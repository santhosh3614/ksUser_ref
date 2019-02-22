package com.kstransfter.models.app;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SONI on 12/16/2018.
 */

public class DriverListModel implements Parcelable {
    @SerializedName("responseCode")
    @Expose
    private Integer responseCode;
    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("responseData")
    @Expose
    private List<DriverListModel.ResponseDatum> responseData = null;

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

    public List<DriverListModel.ResponseDatum> getResponseData() {
        return responseData;
    }

    public void setResponseData(List<DriverListModel.ResponseDatum> responseData) {
        this.responseData = responseData;
    }



    public static class ResponseDatum implements Parcelable {

        @SerializedName("iDriverId")
        @Expose
        private String iDriverId;
        @SerializedName("vDriverName")
        @Expose
        private String vDriverName;
        @SerializedName("vDriverImage")
        @Expose
        private String vDriverImage;
        @SerializedName("vLicenceNumber")
        @Expose
        private String vLicenceNumber;
        @SerializedName("vDriverExp")
        @Expose
        private String vDriverExp;
        @SerializedName("iDriverContactNo")
        @Expose
        private String iDriverContactNo;
        @SerializedName("minKm")
        @Expose
        private String minKm;
        @SerializedName("minKmCharge")
        @Expose
        private Integer minKmCharge;
        @SerializedName("totalPrice")
        @Expose
        private Double totalPrice;
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
        private String driverAllownace;
        @SerializedName("GSTPer")
        @Expose
        private String gSTPer;

        @SerializedName("vCarExp")
        @Expose
        private String vCarExp;

        public String getvCarExp() {
            return vCarExp;
        }

        public void setvCarExp(String vCarExp) {
            this.vCarExp = vCarExp;
        }

        public String getIDriverId() {
            return iDriverId;
        }

        public void setIDriverId(String iDriverId) {
            this.iDriverId = iDriverId;
        }

        public String getVDriverName() {
            return vDriverName;
        }

        public void setVDriverName(String vDriverName) {
            this.vDriverName = vDriverName;
        }

        public String getVDriverImage() {
            return vDriverImage;
        }

        public void setVDriverImage(String vDriverImage) {
            this.vDriverImage = vDriverImage;
        }

        public String getVLicenceNumber() {
            return vLicenceNumber;
        }

        public void setVLicenceNumber(String vLicenceNumber) {
            this.vLicenceNumber = vLicenceNumber;
        }

        public String getVDriverExp() {
            return vDriverExp;
        }

        public void setVDriverExp(String vDriverExp) {
            this.vDriverExp = vDriverExp;
        }

        public String getIDriverContactNo() {
            return iDriverContactNo;
        }

        public void setIDriverContactNo(String iDriverContactNo) {
            this.iDriverContactNo = iDriverContactNo;
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

        public Double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
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

        public String getDriverAllownace() {
            return driverAllownace;
        }

        public void setDriverAllownace(String driverAllownace) {
            this.driverAllownace = driverAllownace;
        }

        public String getGSTPer() {
            return gSTPer;
        }

        public void setGSTPer(String gSTPer) {
            this.gSTPer = gSTPer;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.iDriverId);
            dest.writeString(this.vDriverName);
            dest.writeString(this.vDriverImage);
            dest.writeString(this.vLicenceNumber);
            dest.writeString(this.vDriverExp);
            dest.writeString(this.iDriverContactNo);
            dest.writeString(this.minKm);
            dest.writeValue(this.minKmCharge);
            dest.writeValue(this.totalPrice);
            dest.writeValue(this.extraKm);
            dest.writeValue(this.extraKmcharge);
            dest.writeValue(this.driverNightCharge);
            dest.writeString(this.driverAllownace);
            dest.writeString(this.vCarExp);
        }

        public ResponseDatum() {
        }

        protected ResponseDatum(Parcel in) {
            this.iDriverId = in.readString();
            this.vDriverName = in.readString();
            this.vDriverImage = in.readString();
            this.vLicenceNumber = in.readString();
            this.vDriverExp = in.readString();
            this.iDriverContactNo = in.readString();
            this.minKm = in.readString();
            this.minKmCharge = (Integer) in.readValue(Integer.class.getClassLoader());
            this.totalPrice = (Double) in.readValue(Double.class.getClassLoader());
            this.extraKm = (Integer) in.readValue(Integer.class.getClassLoader());
            this.extraKmcharge = (Integer) in.readValue(Integer.class.getClassLoader());
            this.driverNightCharge = (Integer) in.readValue(Integer.class.getClassLoader());
            this.driverAllownace = in.readString();
            this.gSTPer = in.readString();
            this.vCarExp = in.readString();
         }

        public static final Parcelable.Creator<ResponseDatum> CREATOR = new Parcelable.Creator<ResponseDatum>() {
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

    public DriverListModel() {
    }

    protected DriverListModel(Parcel in) {
        this.responseCode = (Integer) in.readValue(Integer.class.getClassLoader());
        this.responseMessage = in.readString();
        this.responseData = in.createTypedArrayList(ResponseDatum.CREATOR);
    }

    public static final Parcelable.Creator<DriverListModel> CREATOR = new Parcelable.Creator<DriverListModel>() {
        @Override
        public DriverListModel createFromParcel(Parcel source) {
            return new DriverListModel(source);
        }

        @Override
        public DriverListModel[] newArray(int size) {
            return new DriverListModel[size];
        }
    };
}
