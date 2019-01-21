package com.kstransfter.models.app;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SONI on 12/16/2018.
 */

public class CarListtModel implements Parcelable {

    @SerializedName("responseCode")
    @Expose
    private Integer responseCode;
    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("responseData")
    @Expose
    private List<ResponseDatum> responseData = null;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public List<ResponseDatum> getResponseData() {
        return responseData;
    }

    public void setResponseData(List<ResponseDatum> responseData) {
        this.responseData = responseData;
    }

    public static class ResponseDatum implements Parcelable {

        @SerializedName("iDriverId")
        @Expose
        private String iDriverId;
        @SerializedName("iCarCetegoryId")
        @Expose
        private String iCarCetegoryId;
        @SerializedName("vCar")
        @Expose
        private String vCar;
        @SerializedName("vCarImage")
        @Expose
        private String vCarImage;
        @SerializedName("vCarNumber")
        @Expose
        private String vCarNumber;
        @SerializedName("iPrice")
        @Expose
        private String iPrice;
        @SerializedName("iSeater")
        @Expose
        private String iSeater;
        @SerializedName("tiIsAc")
        @Expose
        private String tiIsAc;
        @SerializedName("vDriverName")
        @Expose
        private String vDriverName;
        @SerializedName("vLicenceNumber")
        @Expose
        private String vLicenceNumber;
        @SerializedName("iDriverContactNo")
        @Expose
        private String iDriverContactNo;
        @SerializedName("iDriverAlterContactNo")
        @Expose
        private String iDriverAlterContactNo;
        @SerializedName("vDriverEmail")
        @Expose
        private String vDriverEmail;
        @SerializedName("totalPrice")
        @Expose
        private Integer totalPrice;
        @SerializedName("minKm")
        @Expose
        private String minKm;
        @SerializedName("minKmCharge")
        @Expose
        private Integer minKmCharge;
        @SerializedName("extraKm")
        @Expose
        private Integer extraKm;
        @SerializedName("extraKmcharge")
        @Expose
        private Integer extraKmcharge;
        @SerializedName("driverNightCharge")
        @Expose
        private Integer driverNightCharge;
        @SerializedName("driverAllownace")
        @Expose
        private Integer driverAllownace;
        @SerializedName("GSTPer")
        @Expose
        private String gSTPer;
        @SerializedName("GSTRs")
        @Expose
        private double gSTRs;
        @SerializedName("offerDiscount")
        @Expose
        private Integer offerDiscount;

        public String getIDriverId() {
            return iDriverId;
        }

        public void setIDriverId(String iDriverId) {
            this.iDriverId = iDriverId;
        }

        public Object getICarCetegoryId() {
            return iCarCetegoryId;
        }

        public void setICarCetegoryId(String iCarCetegoryId) {
            this.iCarCetegoryId = iCarCetegoryId;
        }

        public Object getVCar() {
            return vCar;
        }

        public void setVCar(String vCar) {
            this.vCar = vCar;
        }

        public String getVCarImage() {
            return vCarImage;
        }

        public void setVCarImage(String vCarImage) {
            this.vCarImage = vCarImage;
        }

        public String getVCarNumber() {
            return vCarNumber;
        }

        public void setVCarNumber(String vCarNumber) {
            this.vCarNumber = vCarNumber;
        }

        public Object getIPrice() {
            return iPrice;
        }

        public void setIPrice(String iPrice) {
            this.iPrice = iPrice;
        }

        public Object getISeater() {
            return iSeater;
        }

        public void setISeater(String iSeater) {
            this.iSeater = iSeater;
        }

        public Object getTiIsAc() {
            return tiIsAc;
        }

        public void setTiIsAc(String tiIsAc) {
            this.tiIsAc = tiIsAc;
        }

        public Object getVDriverName() {
            return vDriverName;
        }

        public void setVDriverName(String vDriverName) {
            this.vDriverName = vDriverName;
        }

        public String getVLicenceNumber() {
            return vLicenceNumber;
        }

        public void setVLicenceNumber(String vLicenceNumber) {
            this.vLicenceNumber = vLicenceNumber;
        }

        public String getIDriverContactNo() {
            return iDriverContactNo;
        }

        public void setIDriverContactNo(String iDriverContactNo) {
            this.iDriverContactNo = iDriverContactNo;
        }

        public String getIDriverAlterContactNo() {
            return iDriverAlterContactNo;
        }

        public void setIDriverAlterContactNo(String iDriverAlterContactNo) {
            this.iDriverAlterContactNo = iDriverAlterContactNo;
        }

        public String getVDriverEmail() {
            return vDriverEmail;
        }

        public void setVDriverEmail(String vDriverEmail) {
            this.vDriverEmail = vDriverEmail;
        }

        public Integer getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(Integer totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getMinKm() {
            return minKm;
        }

        public void setMinKm(String minKm) {
            this.minKm = minKm;
        }

        public Integer getMinKmCharge() {
            return minKmCharge;
        }

        public void setMinKmCharge(Integer minKmCharge) {
            this.minKmCharge = minKmCharge;
        }

        public Integer getExtraKm() {
            return extraKm;
        }

        public void setExtraKm(Integer extraKm) {
            this.extraKm = extraKm;
        }

        public Integer getExtraKmcharge() {
            return extraKmcharge;
        }

        public void setExtraKmcharge(Integer extraKmcharge) {
            this.extraKmcharge = extraKmcharge;
        }

        public Integer getDriverNightCharge() {
            return driverNightCharge;
        }

        public void setDriverNightCharge(Integer driverNightCharge) {
            this.driverNightCharge = driverNightCharge;
        }

        public Integer getDriverAllownace() {
            return driverAllownace;
        }

        public void setDriverAllownace(Integer driverAllownace) {
            this.driverAllownace = driverAllownace;
        }

        public String getGSTPer() {
            return gSTPer;
        }

        public void setGSTPer(String gSTPer) {
            this.gSTPer = gSTPer;
        }

        public double getGSTRs() {
            return gSTRs;
        }

        public void setGSTRs(Integer gSTRs) {
            this.gSTRs = gSTRs;
        }

        public Integer getOfferDiscount() {
            return offerDiscount;
        }

        public void setOfferDiscount(Integer offerDiscount) {
            this.offerDiscount = offerDiscount;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.iDriverId);
            dest.writeString(this.iCarCetegoryId);
            dest.writeString(this.vCar);
            dest.writeString(this.vCarImage);
            dest.writeString(this.vCarNumber);
            dest.writeString(this.iPrice);
            dest.writeString(this.iSeater);
            dest.writeString(this.tiIsAc);
            dest.writeString(this.vDriverName);
            dest.writeString(this.vLicenceNumber);
            dest.writeString(this.iDriverContactNo);
            dest.writeString(this.iDriverAlterContactNo);
            dest.writeString(this.vDriverEmail);
            dest.writeValue(this.totalPrice);
            dest.writeString(this.minKm);
            dest.writeValue(this.minKmCharge);
            dest.writeValue(this.extraKm);
            dest.writeValue(this.extraKmcharge);
            dest.writeValue(this.driverNightCharge);
            dest.writeValue(this.driverAllownace);
            dest.writeString(this.gSTPer);
            dest.writeValue(this.gSTRs);
            dest.writeValue(this.offerDiscount);
        }

        public ResponseDatum() {
        }

        protected ResponseDatum(Parcel in) {
            this.iDriverId = in.readString();
            this.iCarCetegoryId = in.readString();
            this.vCar = in.readString();
            this.vCarImage = in.readString();
            this.vCarNumber = in.readString();
            this.iPrice = in.readString();
            this.iSeater = in.readString();
            this.tiIsAc = in.readString();
            this.vDriverName = in.readString();
            this.vLicenceNumber = in.readString();
            this.iDriverContactNo = in.readString();
            this.iDriverAlterContactNo = in.readString();
            this.vDriverEmail = in.readString();
            this.totalPrice = (Integer) in.readValue(Integer.class.getClassLoader());
            this.minKm = in.readString();
            this.minKmCharge = (Integer) in.readValue(Integer.class.getClassLoader());
            this.extraKm = (Integer) in.readValue(Integer.class.getClassLoader());
            this.extraKmcharge = (Integer) in.readValue(Integer.class.getClassLoader());
            this.driverNightCharge = (Integer) in.readValue(Integer.class.getClassLoader());
            this.driverAllownace = (Integer) in.readValue(Integer.class.getClassLoader());
            this.gSTPer = in.readString();
            this.gSTRs = (Integer) in.readValue(Integer.class.getClassLoader());
            this.offerDiscount = (Integer) in.readValue(Integer.class.getClassLoader());
        }

        public static final Creator<ResponseDatum> CREATOR = new Creator<ResponseDatum>() {
            @Override
            public ResponseDatum createFromParcel(Parcel source) {
                return new ResponseDatum(source);
            }

            @Override
            public ResponseDatum[] newArray(int size) {
                return new ResponseDatum[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.responseCode);
        dest.writeString(this.responseMessage);
        dest.writeTypedList(this.responseData);
    }

    public CarListtModel() {
    }

    protected CarListtModel(Parcel in) {
        this.responseCode = (Integer) in.readValue(Integer.class.getClassLoader());
        this.responseMessage = in.readString();
        this.responseData = in.createTypedArrayList(ResponseDatum.CREATOR);
    }

    public static final Parcelable.Creator<CarListtModel> CREATOR = new Parcelable.Creator<CarListtModel>() {
        @Override
        public CarListtModel createFromParcel(Parcel source) {
            return new CarListtModel(source);
        }

        @Override
        public CarListtModel[] newArray(int size) {
            return new CarListtModel[size];
        }
    };
}
