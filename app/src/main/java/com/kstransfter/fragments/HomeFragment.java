package com.kstransfter.fragments;

import android.Manifest;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.JsonObject;
import com.kstransfter.R;
import com.kstransfter.activities.MainActivity;
import com.kstransfter.activities.MapsActivity;
import com.kstransfter.adapters.CarListAdapter;
import com.kstransfter.interfaces.ApiInterface;
import com.kstransfter.models.AutocompleteAddrees;
import com.kstransfter.models.Result;
import com.kstransfter.models.Route;
import com.kstransfter.models.app.CarListModel;
import com.kstransfter.models.events.BeginJourneyEvent;
import com.kstransfter.models.events.CurrentJourneyEvent;
import com.kstransfter.models.events.EndJourneyEvent;
import com.kstransfter.utils.JourneyEventBus;
import com.kstransfter.utils.PoupUtils;
import com.kstransfter.utils.StaticUtils;
import com.kstransfter.webservice.WsFactory;
import com.kstransfter.webservice.WsResponse;
import com.kstransfter.webservice.WsUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.google.android.gms.maps.model.JointType.ROUND;

public class HomeFragment extends BaseFragment implements OnMapReadyCallback {

    private static final String TAG = HomeFragment.class.getSimpleName();

    private static final int REQUEST_LOCATION = 1111;
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
    private RecyclerView rvCarList;
    private ArrayList<CarListModel> carListModels = new ArrayList<>();
    private CarListAdapter carListAdapter;
    private TextView txtCallDriver;
    private RelativeLayout llRuning, rlcarAndDriver;
    private LinearLayout llBottomAfterRide, llDrop, llPickUp, llBeforeRide;
    private EditText edtPickUpLine, edtDropLine;
    private FusedLocationProviderClient mFusedLocationClient;
    private ImageView imgCurrentLoaction;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE1 = 1;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE2 = 11;
    private double latitude = 0.0;
    private double longitude = 0.0;
    private String pickupAddress;
    private String dropAddress;
    private TextView txtRideLater;
    private MainActivity mainActivity;


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
        llRuning = view.findViewById(R.id.llRuning);
        llBottomAfterRide = view.findViewById(R.id.llBottomAfterRide);
        rvCarList = view.findViewById(R.id.rvCarList);
        llDrop = view.findViewById(R.id.llDrop);
        llPickUp = view.findViewById(R.id.llPickUp);
        llBeforeRide = view.findViewById(R.id.llBeforeRide);
        edtPickUpLine = view.findViewById(R.id.edtPickUpLine);
        edtDropLine = view.findViewById(R.id.edtDropLine);
        imgCurrentLoaction = view.findViewById(R.id.imgCurrentLoaction);
        txtRideLater = view.findViewById(R.id.txtRideLater);
        mainActivity = (MainActivity) getActivity();
        setVisibleAndGone();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        rvCarList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        setCarAdapter();
        //Show Current location:
        getCurrentLoction();
        try {
            initital();
        } catch (Exception e) {
            e.printStackTrace();
        }


        edtPickUpLine.setOnClickListener(v -> {
          /*  try {
                Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(getActivity());
                startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE1);
              } catch (GooglePlayServicesRepairableException e) {
                // TODO: Handle the error.
              } catch (GooglePlayServicesNotAvailableException e) {
                // TODO: Handle the error.
            }*/
            wsCallingHere();
        });


        edtDropLine.setOnClickListener(v -> {
            try {
                Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(getActivity());
                startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE2);
            } catch (GooglePlayServicesRepairableException e) {
                // TODO: Handle the error.
            } catch (GooglePlayServicesNotAvailableException e) {
                // TODO: Handle the error.
            }

        });


        imgCurrentLoaction.setOnClickListener(v -> {
            getCurrentLoction();
        });

        txtRideNow.setOnClickListener(v -> {
            pickupAddress = edtPickUpLine.getText().toString().trim();
            dropAddress = edtDropLine.getText().toString().trim();
            boolean isSelectedCar = false;
            for (int i = 0; i < carListModels.size(); i++) {
                CarListModel carListModel = carListModels.get(i);
                if (carListModel.isSelected()) {
                    isSelectedCar = true;
                    break;
                 } else {
                    continue;
                }
            }

            if (TextUtils.isEmpty(pickupAddress)) {
                Toast.makeText(getContext(), "Add pickup address", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(dropAddress)) {
                Toast.makeText(getContext(), "Add drop address", Toast.LENGTH_SHORT).show();
            } else if (!isSelectedCar) {
                Toast.makeText(getContext(), "Pelase select at least a car", Toast.LENGTH_SHORT).show();
            } else {
                movecarSourcetoDesignation();
                llDrop.setVisibility(View.GONE);
                llPickUp.setVisibility(View.GONE);
                llBeforeRide.setVisibility(View.GONE);
                llRuning.setVisibility(View.VISIBLE);
                llBottomAfterRide.setVisibility(View.VISIBLE);
            }
        });

        txtRideLater.setOnClickListener(v -> {
            PoupUtils.showDatePicker(getContext());
        });
    }

    private void wsCallingHere() {
//        progressDialog.show();

        Call loginWsCall = WsFactory.getAutoCompleteAddress(WsUtils.API_Key, "delhi");
        WsUtils.getReponse(loginWsCall, StaticUtils.REQUEST_FOR_AUOTOCOMPLETE_ADDRESS, new WsResponse() {
            @Override
            public void successResponse(Object response, int code) {
                AutocompleteAddrees autocompleteAddrees = (AutocompleteAddrees) response;
                Log.e("Response: ", "" + autocompleteAddrees);
            }

            @Override
            public void failureRespons(Throwable error, int code) {
//                Log.e("Error: ", "" + error);

            }
        });
    }

    private void setVisibleAndGone() {
        mainActivity.txtLocalRides.setVisibility(View.VISIBLE);
        mainActivity.txtOutSideRide.setVisibility(View.VISIBLE);
        mainActivity.imgMenu.setVisibility(View.VISIBLE);
        mainActivity.imgBack.setVisibility(View.GONE);
        mainActivity.txtTitle.setVisibility(View.GONE);
    }

    private void movecarSourcetoDesignation() {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://maps.googleapis.com/")
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
        sydney = new LatLng(latitude, longitude);

        apiInterface.getDirections("driving",
                "less_driving",
                latitude + "," + longitude, dropAddress,
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

    private void getCurrentLoction() {
        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION
                    },
                    REQUEST_LOCATION);
        } else {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                double currentLat = location.getLatitude();
                                double currentLont = location.getLongitude();
                                LatLng latLng = new LatLng(currentLat, currentLont);
                                addMarkerOnCurrentLocation(latLng);
                            }
                        }
                    });
        }
    }


    private void addMarkerOnCurrentLocation(LatLng currentlatLng) {
        mMap.addMarker(new MarkerOptions().position(currentlatLng).
                icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentlatLng));
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder()
                .target(mMap.getCameraPosition().target)
                .zoom(12)
                .bearing(30)
                .tilt(45)
                .build()));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if (permissions[0].equals(Manifest.permission.ACCESS_COARSE_LOCATION)
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLoction();

            }
        }
    }

    //Will call later
    private void carRouting() {
        destination = destinationEditText.getText().toString();
        destination = destination.replace(" ", "+");
        Log.d(TAG, destination);
        mapFragment.getMapAsync(HomeFragment.this);
    }

    private void setCarAdapter() {
        carListModels.clear();
        for (int i = 0; i < 20; i++) {
            CarListModel carListModel = new CarListModel();
            carListModel.setCarName("car" + i);
            carListModel.setSelected(false);
            carListModels.add(carListModel);
        }
        carListAdapter = new CarListAdapter(
                getContext(),
                carListModels,
                (view, pos) -> {

                });
        rvCarList.setAdapter(carListAdapter);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setTrafficEnabled(false);
        mMap.setIndoorEnabled(false);
        mMap.setBuildingsEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        // Add a marker in Home and move the camera
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
        blackPolylineOptions.color(Color.YELLOW);
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
        mapFragment.getMapAsync(HomeFragment.this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE1) {
            if (resultCode == getActivity().RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(getContext(), data);
                edtPickUpLine.setText(place.getAddress());
                this.latitude = place.getLatLng().latitude;
                this.longitude = place.getLatLng().longitude;
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getContext(), data);
                Log.i(TAG, status.getStatusMessage());
            } else if (resultCode == getActivity().RESULT_CANCELED) {
                // The user canceled the operation.
                Toast.makeText(getContext(), "Canceled:", Toast.LENGTH_SHORT).show();
            }


        } else if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE2) {
            if (resultCode == getActivity().RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(getContext(), data);
                edtDropLine.setText(place.getAddress());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getContext(), data);
                Log.i(TAG, status.getStatusMessage());
            } else if (resultCode == getActivity().RESULT_CANCELED) {
                // The user canceled the operation.
                Toast.makeText(getContext(), "Canceled:", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

