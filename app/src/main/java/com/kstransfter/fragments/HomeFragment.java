package com.kstransfter.fragments;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.SquareCap;
import com.kstransfter.R;
import com.kstransfter.activities.MapsActivity;
import com.kstransfter.interfaces.ApiInterface;
import com.kstransfter.models.Result;
import com.kstransfter.models.Route;
import com.kstransfter.models.events.BeginJourneyEvent;
import com.kstransfter.models.events.CurrentJourneyEvent;
import com.kstransfter.models.events.EndJourneyEvent;
import com.kstransfter.utils.JourneyEventBus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.google.android.gms.maps.model.JointType.ROUND;

public class HomeFragment extends BaseFragment implements OnMapReadyCallback {
    private static final String TAG = MapsActivity.class.getSimpleName();
    SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private List<LatLng> polyLineList;
    private Marker marker;
    private float v;
    private double lat, lng;
    private Handler handler;
    private LatLng startPosition, endPosition;
    private int index, next;
    private LatLng sydney;
    private EditText destinationEditText;
    private String destination;
    private RelativeLayout rlHome;
    private PolylineOptions polylineOptions, blackPolylineOptions;
    private Polyline blackPolyline, greyPolyLine;
    private ApiInterface apiInterface;
    private Disposable disposable;
    private TextView txtRideNow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        rlHome = view.findViewById(R.id.rlHome);
        polyLineList = new ArrayList<>();
        txtRideNow = view.findViewById(R.id.txtRideNow);
        destinationEditText = view.findViewById(R.id.edtDropLine);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://maps.googleapis.com/")
                .build();
        apiInterface = retrofit.create(ApiInterface.class);

        txtRideNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destination = destinationEditText.getText().toString();
                destination = destination.replace(" ", "+");
                Log.d(TAG, destination);
                mapFragment.getMapAsync(HomeFragment.this);
            }
        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        final double latitude = 28.671246;
        double longitude = 77.317654;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setTrafficEnabled(false);
        mMap.setIndoorEnabled(false);
        mMap.setBuildingsEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        // Add a marker in Home and move the camera
        sydney = new LatLng(28.671246, 77.317654);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Home"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder()
                .target(googleMap.getCameraPosition().target)
                .zoom(17)
                .bearing(30)
                .tilt(45)
                .build()));


        apiInterface.getDirections("driving",
                "less_driving",
                latitude + "," + longitude, destination,
                getResources().getString(R.string.google_directions_key))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new SingleObserver<Result>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(Result result) {
                                List<Route> routeList = result.getRoutes();
                                for (Route route : routeList) {
                                    String polyLine = route.getOverviewPolyline().getPoints();
                                    polyLineList = decodePoly(polyLine);
                                    drawPolyLineAndAnimateCar();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }
                        });

    }


    private void drawPolyLineAndAnimateCar() {
        //Adjusting bounds
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (LatLng latLng : polyLineList) {
            builder.include(latLng);
        }
        LatLngBounds bounds = builder.build();
        CameraUpdate mCameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 2);
        mMap.animateCamera(mCameraUpdate);

        polylineOptions = new PolylineOptions();
        polylineOptions.color(Color.GRAY);
        polylineOptions.width(5);
        polylineOptions.startCap(new SquareCap());
        polylineOptions.endCap(new SquareCap());
        polylineOptions.jointType(ROUND);
        polylineOptions.addAll(polyLineList);
        greyPolyLine = mMap.addPolyline(polylineOptions);

        blackPolylineOptions = new PolylineOptions();
        blackPolylineOptions.width(5);
        blackPolylineOptions.color(Color.BLACK);
        blackPolylineOptions.startCap(new SquareCap());
        blackPolylineOptions.endCap(new SquareCap());
        blackPolylineOptions.jointType(ROUND);
        blackPolyline = mMap.addPolyline(blackPolylineOptions);

        mMap.addMarker(new MarkerOptions()
                .position(polyLineList.get(polyLineList.size() - 1)));

        ValueAnimator polylineAnimator = ValueAnimator.ofInt(0, 100);
        polylineAnimator.setDuration(2000);
        polylineAnimator.setInterpolator(new LinearInterpolator());
        polylineAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                List<LatLng> points = greyPolyLine.getPoints();
                int percentValue = (int) valueAnimator.getAnimatedValue();
                int size = points.size();
                int newPoints = (int) (size * (percentValue / 100.0f));
                List<LatLng> p = points.subList(0, newPoints);
                blackPolyline.setPoints(p);
            }
        });
        polylineAnimator.start();
        marker = mMap.addMarker(new MarkerOptions().position(sydney).flat(true).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_directions_car_black_24dp)));
        handler = new Handler();
        index = -1;
        next = 1;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (index < polyLineList.size() - 1) {
                    index++;
                    next = index + 1;
                }
                if (index < polyLineList.size() - 1) {
                    startPosition = polyLineList.get(index);
                    endPosition = polyLineList.get(next);
                }
                if (index == 0) {
                    BeginJourneyEvent beginJourneyEvent = new BeginJourneyEvent();
                    beginJourneyEvent.setBeginLatLng(startPosition);
                    JourneyEventBus.getInstance().setOnJourneyBegin(beginJourneyEvent);
                }
                if (index == polyLineList.size() - 1) {
                    EndJourneyEvent endJourneyEvent = new EndJourneyEvent();
                    endJourneyEvent.setEndJourneyLatLng(new LatLng(polyLineList.get(index).latitude,
                            polyLineList.get(index).longitude));
                    JourneyEventBus.getInstance().setOnJourneyEnd(endJourneyEvent);
                }
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
                valueAnimator.setDuration(3000);
                valueAnimator.setInterpolator(new LinearInterpolator());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        v = valueAnimator.getAnimatedFraction();
                        lng = v * endPosition.longitude + (1 - v)
                                * startPosition.longitude;
                        lat = v * endPosition.latitude + (1 - v)
                                * startPosition.latitude;
                        LatLng newPos = new LatLng(lat, lng);
                        CurrentJourneyEvent currentJourneyEvent = new CurrentJourneyEvent();
                        currentJourneyEvent.setCurrentLatLng(newPos);
                        JourneyEventBus.getInstance().setOnJourneyUpdate(currentJourneyEvent);
                        marker.setPosition(newPos);
                        marker.setAnchor(0.5f, 0.5f);
                        marker.setRotation(getBearing(startPosition, newPos));
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition
                                (new CameraPosition.Builder().target(newPos)
                                        .zoom(15.5f).build()));
                    }
                });
                valueAnimator.start();
                if (index != polyLineList.size() - 1) {
                    handler.postDelayed(this, 3000);
                }
            }
        }, 3000);
    }


    private List<LatLng> decodePoly(String encoded) {
        List<LatLng> poly = new ArrayList<>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }

    private float getBearing(LatLng begin, LatLng end) {
        double lat = Math.abs(begin.latitude - end.latitude);
        double lng = Math.abs(begin.longitude - end.longitude);

        if (begin.latitude < end.latitude && begin.longitude < end.longitude)
            return (float) (Math.toDegrees(Math.atan(lng / lat)));
        else if (begin.latitude >= end.latitude && begin.longitude < end.longitude)
            return (float) ((90 - Math.toDegrees(Math.atan(lng / lat))) + 90);
        else if (begin.latitude >= end.latitude && begin.longitude >= end.longitude)
            return (float) (Math.toDegrees(Math.atan(lng / lat)) + 180);
        else if (begin.latitude < end.latitude && begin.longitude >= end.longitude)
            return (float) ((90 - Math.toDegrees(Math.atan(lng / lat))) + 270);
        return -1;
    }


    @Override
    public void onResume() {
        super.onResume();
        //This is an event bus for receiving journey events this can be shifted anywhere
        //in code.
        //Do remember to dispose when not in use. For eg. its necessary to dispose it in
        //onStop as activity is not visible.
        disposable = JourneyEventBus.getInstance().getOnJourneyEvent()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (o instanceof BeginJourneyEvent) {
                            Snackbar.make(rlHome, "Journey has started",
                                    Snackbar.LENGTH_SHORT).show();
                        } else if (o instanceof EndJourneyEvent) {
                            Snackbar.make(rlHome, "Journey has ended",
                                    Snackbar.LENGTH_SHORT).show();
                        } else if (o instanceof CurrentJourneyEvent) {
                            /*
                             * This can be used to receive the current location update of the car
                             */
                            //Log.d(TAG,"Current "+((CurrentJourneyEvent) o).getCurrentLatLng());
                        }
                    }
                });
    }

    @Override
    public void onStop() {
        super.onStop();
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }


    @Override
    public void initital() {

    }


}

