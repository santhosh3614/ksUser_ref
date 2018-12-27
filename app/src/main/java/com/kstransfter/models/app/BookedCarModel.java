package com.kstransfter.models.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookedCarModel {

    @SerializedName("responseCode")
    @Expose
    private Integer responseCode;
    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("responseData")
    @Expose
    private ResponseData responseData;

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

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }

    public static class ResponseData {
        @SerializedName("txPickUpAddress")
        @Expose
        private String txPickUpAddress;
        @SerializedName("iDriverId")
        @Expose
        private String iDriverId;
        @SerializedName("iUserId")
        @Expose
        private String iUserId;
        @SerializedName("dcPickUpLatitude")
        @Expose
        private String dcPickUpLatitude;
        @SerializedName("dcPickUpLongitude")
        @Expose
        private String dcPickUpLongitude;
        @SerializedName("vPickUpCity")
        @Expose
        private String vPickUpCity;
        @SerializedName("txReturnAddress")
        @Expose
        private Object txReturnAddress;
        @SerializedName("dcReturnLatitude")
        @Expose
        private Object dcReturnLatitude;
        @SerializedName("dcReturnLongitude")
        @Expose
        private Object dcReturnLongitude;
        @SerializedName("dtLeavingDateTime")
        @Expose
        private String dtLeavingDateTime;
        @SerializedName("iLeavingTimestamp")
        @Expose
        private Integer iLeavingTimestamp;
        @SerializedName("iReturningTimestamp")
        @Expose
        private Object iReturningTimestamp;
        @SerializedName("dtReturningDateTime")
        @Expose
        private Object dtReturningDateTime;
        @SerializedName("vTotalDayDifference")
        @Expose
        private Integer vTotalDayDifference;
        @SerializedName("vTotalHourDifference")
        @Expose
        private String vTotalHourDifference;
        @SerializedName("vDistance")
        @Expose
        private String vDistance;
        @SerializedName("dtCreatedAt")
        @Expose
        private String dtCreatedAt;
        @SerializedName("dtUpdatedAt")
        @Expose
        private String dtUpdatedAt;
        @SerializedName("iUserBookingId")
        @Expose
        private Integer iUserBookingId;
        @SerializedName("iWating_rs")
        @Expose
        private Integer iWatingRs;
        @SerializedName("dbTotalPrice")
        @Expose
        private Double dbTotalPrice;

        public String getTxPickUpAddress() {
            return txPickUpAddress;
        }

        public void setTxPickUpAddress(String txPickUpAddress) {
            this.txPickUpAddress = txPickUpAddress;
        }

        public String getIDriverId() {
            return iDriverId;
        }

        public void setIDriverId(String iDriverId) {
            this.iDriverId = iDriverId;
        }

        public String getIUserId() {
            return iUserId;
        }

        public void setIUserId(String iUserId) {
            this.iUserId = iUserId;
        }

        public String getDcPickUpLatitude() {
            return dcPickUpLatitude;
        }

        public void setDcPickUpLatitude(String dcPickUpLatitude) {
            this.dcPickUpLatitude = dcPickUpLatitude;
        }

        public String getDcPickUpLongitude() {
            return dcPickUpLongitude;
        }

        public void setDcPickUpLongitude(String dcPickUpLongitude) {
            this.dcPickUpLongitude = dcPickUpLongitude;
        }

        public String getVPickUpCity() {
            return vPickUpCity;
        }

        public void setVPickUpCity(String vPickUpCity) {
            this.vPickUpCity = vPickUpCity;
        }

        public Object getTxReturnAddress() {
            return txReturnAddress;
        }

        public void setTxReturnAddress(Object txReturnAddress) {
            this.txReturnAddress = txReturnAddress;
        }

        public Object getDcReturnLatitude() {
            return dcReturnLatitude;
        }

        public void setDcReturnLatitude(Object dcReturnLatitude) {
            this.dcReturnLatitude = dcReturnLatitude;
        }

        public Object getDcReturnLongitude() {
            return dcReturnLongitude;
        }

        public void setDcReturnLongitude(Object dcReturnLongitude) {
            this.dcReturnLongitude = dcReturnLongitude;
        }

        public String getDtLeavingDateTime() {
            return dtLeavingDateTime;
        }

        public void setDtLeavingDateTime(String dtLeavingDateTime) {
            this.dtLeavingDateTime = dtLeavingDateTime;
        }

        public Integer getILeavingTimestamp() {
            return iLeavingTimestamp;
        }

        public void setILeavingTimestamp(Integer iLeavingTimestamp) {
            this.iLeavingTimestamp = iLeavingTimestamp;
        }

        public Object getIReturningTimestamp() {
            return iReturningTimestamp;
        }

        public void setIReturningTimestamp(Object iReturningTimestamp) {
            this.iReturningTimestamp = iReturningTimestamp;
        }

        public Object getDtReturningDateTime() {
            return dtReturningDateTime;
        }

        public void setDtReturningDateTime(Object dtReturningDateTime) {
            this.dtReturningDateTime = dtReturningDateTime;
        }

        public Integer getVTotalDayDifference() {
            return vTotalDayDifference;
        }

        public void setVTotalDayDifference(Integer vTotalDayDifference) {
            this.vTotalDayDifference = vTotalDayDifference;
        }

        public String getVTotalHourDifference() {
            return vTotalHourDifference;
        }

        public void setVTotalHourDifference(String vTotalHourDifference) {
            this.vTotalHourDifference = vTotalHourDifference;
        }

        public String getVDistance() {
            return vDistance;
        }

        public void setVDistance(String vDistance) {
            this.vDistance = vDistance;
        }

        public String getDtCreatedAt() {
            return dtCreatedAt;
        }

        public void setDtCreatedAt(String dtCreatedAt) {
            this.dtCreatedAt = dtCreatedAt;
        }

        public String getDtUpdatedAt() {
            return dtUpdatedAt;
        }

        public void setDtUpdatedAt(String dtUpdatedAt) {
            this.dtUpdatedAt = dtUpdatedAt;
        }

        public Integer getIUserBookingId() {
            return iUserBookingId;
        }

        public void setIUserBookingId(Integer iUserBookingId) {
            this.iUserBookingId = iUserBookingId;
        }

        public Integer getIWatingRs() {
            return iWatingRs;
        }

        public void setIWatingRs(Integer iWatingRs) {
            this.iWatingRs = iWatingRs;
        }

        public Double getDbTotalPrice() {
            return dbTotalPrice;
        }

        public void setDbTotalPrice(Double dbTotalPrice) {
            this.dbTotalPrice = dbTotalPrice;
        }

    }


}
