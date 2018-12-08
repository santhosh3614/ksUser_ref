package com.kstransfter.models.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by SONI on 12/9/2018.
 */

public class CarListModel {


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

    public static class ResponseDatum {

        @SerializedName("iDriverId")
        @Expose
        private String iDriverId;
        @SerializedName("iCarCetegoryId")
        @Expose
        private Object iCarCetegoryId;
        @SerializedName("vCar")
        @Expose
        private Object vCar;
        @SerializedName("vCarImage")
        @Expose
        private String vCarImage;
        @SerializedName("vCarNumber")
        @Expose
        private String vCarNumber;
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
        private Object extraKm;
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

        public String getIDriverId() {
            return iDriverId;
        }

        public void setIDriverId(String iDriverId) {
            this.iDriverId = iDriverId;
        }

        public Object getICarCetegoryId() {
            return iCarCetegoryId;
        }

        public void setICarCetegoryId(Object iCarCetegoryId) {
            this.iCarCetegoryId = iCarCetegoryId;
        }

        public Object getVCar() {
            return vCar;
        }

        public void setVCar(Object vCar) {
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

        public String getVDriverName() {
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

        public Object getExtraKm() {
            return extraKm;
        }

        public void setExtraKm(Object extraKm) {
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
    }

}
