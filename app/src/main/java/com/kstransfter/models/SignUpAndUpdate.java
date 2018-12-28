package com.kstransfter.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpAndUpdate {

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

        @SerializedName("iUserId")
        @Expose
        private Integer iUserId;
        @SerializedName("txAuthToken")
        @Expose
        private Integer txAuthToken;
        @SerializedName("vUserName")
        @Expose
        private String vUserName;
        @SerializedName("vEmail")
        @Expose
        private String vEmail;
        @SerializedName("dBirthdate")
        @Expose
        private Object dBirthdate;
        @SerializedName("eGender")
        @Expose
        private Object eGender;
        @SerializedName("txAbout")
        @Expose
        private Object txAbout;
        @SerializedName("txAddress1")
        @Expose
        private Object txAddress1;
        @SerializedName("txAddress2")
        @Expose
        private Object txAddress2;
        @SerializedName("vCity")
        @Expose
        private Object vCity;
        @SerializedName("vState")
        @Expose
        private Object vState;
        @SerializedName("vZipcode")
        @Expose
        private String vZipcode;
        @SerializedName("txProfilePic")
        @Expose
        private Object txProfilePic;
        @SerializedName("vPassword")
        @Expose
        private String vPassword;
        @SerializedName("iMobileExtension")
        @Expose
        private String iMobileExtension;
        @SerializedName("vMobileno")
        @Expose
        private String vMobileno;
        @SerializedName("vVerificationcode")
        @Expose
        private Integer vVerificationcode;
        @SerializedName("dLatitude")
        @Expose
        private Object dLatitude;
        @SerializedName("dLongitude")
        @Expose
        private Object dLongitude;
        @SerializedName("txFacebookId")
        @Expose
        private Object txFacebookId;
        @SerializedName("txGoogleId")
        @Expose
        private Object txGoogleId;
        @SerializedName("iLoginType")
        @Expose
        private Integer iLoginType;
        @SerializedName("eUserType")
        @Expose
        private String eUserType;
        @SerializedName("eStatus")
        @Expose
        private String eStatus;
        @SerializedName("iIsDelete")
        @Expose
        private Integer iIsDelete;
        @SerializedName("iIsApprove")
        @Expose
        private Integer iIsApprove;
        @SerializedName("iCreatedAt")
        @Expose
        private Integer iCreatedAt;
        @SerializedName("iUpdatedAt")
        @Expose
        private Integer iUpdatedAt;
        @SerializedName("iLastlogin")
        @Expose
        private Integer iLastlogin;
        @SerializedName("iCompletedStep")
        @Expose
        private Integer iCompletedStep;
        @SerializedName("iIsOnline")
        @Expose
        private Integer iIsOnline;

        public Integer getIUserId() {
            return iUserId;
        }

        public void setIUserId(Integer iUserId) {
            this.iUserId = iUserId;
        }

        public Integer getTxAuthToken() {
            return txAuthToken;
        }

        public void setTxAuthToken(Integer txAuthToken) {
            this.txAuthToken = txAuthToken;
        }

        public String getVUserName() {
            return vUserName;
        }

        public void setVUserName(String vUserName) {
            this.vUserName = vUserName;
        }

        public String getVEmail() {
            return vEmail;
        }

        public void setVEmail(String vEmail) {
            this.vEmail = vEmail;
        }

        public Object getDBirthdate() {
            return dBirthdate;
        }

        public void setDBirthdate(Object dBirthdate) {
            this.dBirthdate = dBirthdate;
        }

        public Object getEGender() {
            return eGender;
        }

        public void setEGender(Object eGender) {
            this.eGender = eGender;
        }

        public Object getTxAbout() {
            return txAbout;
        }

        public void setTxAbout(Object txAbout) {
            this.txAbout = txAbout;
        }

        public Object getTxAddress1() {
            return txAddress1;
        }

        public void setTxAddress1(Object txAddress1) {
            this.txAddress1 = txAddress1;
        }

        public Object getTxAddress2() {
            return txAddress2;
        }

        public void setTxAddress2(Object txAddress2) {
            this.txAddress2 = txAddress2;
        }

        public Object getVCity() {
            return vCity;
        }

        public void setVCity(Object vCity) {
            this.vCity = vCity;
        }

        public Object getVState() {
            return vState;
        }

        public void setVState(Object vState) {
            this.vState = vState;
        }

        public String getVZipcode() {
            return vZipcode;
        }

        public void setVZipcode(String vZipcode) {
            this.vZipcode = vZipcode;
        }

        public Object getTxProfilePic() {
            return txProfilePic;
        }

        public void setTxProfilePic(Object txProfilePic) {
            this.txProfilePic = txProfilePic;
        }

        public String getVPassword() {
            return vPassword;
        }

        public void setVPassword(String vPassword) {
            this.vPassword = vPassword;
        }

        public String getIMobileExtension() {
            return iMobileExtension;
        }

        public void setIMobileExtension(String iMobileExtension) {
            this.iMobileExtension = iMobileExtension;
        }

        public String getVMobileno() {
            return vMobileno;
        }

        public void setVMobileno(String vMobileno) {
            this.vMobileno = vMobileno;
        }

        public Integer getVVerificationcode() {
            return vVerificationcode;
        }

        public void setVVerificationcode(Integer vVerificationcode) {
            this.vVerificationcode = vVerificationcode;
        }

        public Object getDLatitude() {
            return dLatitude;
        }

        public void setDLatitude(Object dLatitude) {
            this.dLatitude = dLatitude;
        }

        public Object getDLongitude() {
            return dLongitude;
        }

        public void setDLongitude(Object dLongitude) {
            this.dLongitude = dLongitude;
        }

        public Object getTxFacebookId() {
            return txFacebookId;
        }

        public void setTxFacebookId(Object txFacebookId) {
            this.txFacebookId = txFacebookId;
        }

        public Object getTxGoogleId() {
            return txGoogleId;
        }

        public void setTxGoogleId(Object txGoogleId) {
            this.txGoogleId = txGoogleId;
        }

        public Integer getILoginType() {
            return iLoginType;
        }

        public void setILoginType(Integer iLoginType) {
            this.iLoginType = iLoginType;
        }

        public String getEUserType() {
            return eUserType;
        }

        public void setEUserType(String eUserType) {
            this.eUserType = eUserType;
        }

        public String getEStatus() {
            return eStatus;
        }

        public void setEStatus(String eStatus) {
            this.eStatus = eStatus;
        }

        public Integer getIIsDelete() {
            return iIsDelete;
        }

        public void setIIsDelete(Integer iIsDelete) {
            this.iIsDelete = iIsDelete;
        }

        public Integer getIIsApprove() {
            return iIsApprove;
        }

        public void setIIsApprove(Integer iIsApprove) {
            this.iIsApprove = iIsApprove;
        }

        public Integer getICreatedAt() {
            return iCreatedAt;
        }

        public void setICreatedAt(Integer iCreatedAt) {
            this.iCreatedAt = iCreatedAt;
        }

        public Integer getIUpdatedAt() {
            return iUpdatedAt;
        }

        public void setIUpdatedAt(Integer iUpdatedAt) {
            this.iUpdatedAt = iUpdatedAt;
        }

        public Integer getILastlogin() {
            return iLastlogin;
        }

        public void setILastlogin(Integer iLastlogin) {
            this.iLastlogin = iLastlogin;
        }

        public Integer getICompletedStep() {
            return iCompletedStep;
        }

        public void setICompletedStep(Integer iCompletedStep) {
            this.iCompletedStep = iCompletedStep;
        }

        public Integer getIIsOnline() {
            return iIsOnline;
        }

        public void setIIsOnline(Integer iIsOnline) {
            this.iIsOnline = iIsOnline;
        }

    }
}
