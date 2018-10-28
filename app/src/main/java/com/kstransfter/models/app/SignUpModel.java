package com.kstransfter.models.app;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by SONI on 10/28/2018.
 */

public class SignUpModel implements Parcelable {

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



    public class ResponseData implements Parcelable {

        @SerializedName("iUserId")
        @Expose
        private String iUserId;
        @SerializedName("txAuthToken")
        @Expose
        private String txAuthToken;
        @SerializedName("vUserName")
        @Expose
        private Object vUserName;
        @SerializedName("vEmail")
        @Expose
        private Object vEmail;
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
        private Object vPassword;
        @SerializedName("iMobileExtension")
        @Expose
        private String iMobileExtension;
        @SerializedName("vMobileno")
        @Expose
        private String vMobileno;
        @SerializedName("vVerificationcode")
        @Expose
        private String vVerificationcode;
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
        private String iLoginType;
        @SerializedName("eUserType")
        @Expose
        private String eUserType;
        @SerializedName("eStatus")
        @Expose
        private String eStatus;
        @SerializedName("iIsDelete")
        @Expose
        private String iIsDelete;
        @SerializedName("iIsApprove")
        @Expose
        private String iIsApprove;
        @SerializedName("iCreatedAt")
        @Expose
        private String iCreatedAt;
        @SerializedName("iUpdatedAt")
        @Expose
        private String iUpdatedAt;
        @SerializedName("iLastlogin")
        @Expose
        private String iLastlogin;
        @SerializedName("iCompletedStep")
        @Expose
        private String iCompletedStep;
        @SerializedName("iIsOnline")
        @Expose
        private String iIsOnline;

        public String getIUserId() {
            return iUserId;
        }

        public void setIUserId(String iUserId) {
            this.iUserId = iUserId;
        }

        public String getTxAuthToken() {
            return txAuthToken;
        }

        public void setTxAuthToken(String txAuthToken) {
            this.txAuthToken = txAuthToken;
        }

        public Object getVUserName() {
            return vUserName;
        }

        public void setVUserName(Object vUserName) {
            this.vUserName = vUserName;
        }

        public Object getVEmail() {
            return vEmail;
        }

        public void setVEmail(Object vEmail) {
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

        public Object getVPassword() {
            return vPassword;
        }

        public void setVPassword(Object vPassword) {
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

        public String getVVerificationcode() {
            return vVerificationcode;
        }

        public void setVVerificationcode(String vVerificationcode) {
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

        public String getILoginType() {
            return iLoginType;
        }

        public void setILoginType(String iLoginType) {
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

        public String getIIsDelete() {
            return iIsDelete;
        }

        public void setIIsDelete(String iIsDelete) {
            this.iIsDelete = iIsDelete;
        }

        public String getIIsApprove() {
            return iIsApprove;
        }

        public void setIIsApprove(String iIsApprove) {
            this.iIsApprove = iIsApprove;
        }

        public String getICreatedAt() {
            return iCreatedAt;
        }

        public void setICreatedAt(String iCreatedAt) {
            this.iCreatedAt = iCreatedAt;
        }

        public String getIUpdatedAt() {
            return iUpdatedAt;
        }

        public void setIUpdatedAt(String iUpdatedAt) {
            this.iUpdatedAt = iUpdatedAt;
        }

        public String getILastlogin() {
            return iLastlogin;
        }

        public void setILastlogin(String iLastlogin) {
            this.iLastlogin = iLastlogin;
        }

        public String getICompletedStep() {
            return iCompletedStep;
        }

        public void setICompletedStep(String iCompletedStep) {
            this.iCompletedStep = iCompletedStep;
        }

        public String getIIsOnline() {
            return iIsOnline;
        }

        public void setIIsOnline(String iIsOnline) {
            this.iIsOnline = iIsOnline;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.iUserId);
            dest.writeString(this.txAuthToken);
            dest.writeString(this.vZipcode);
            dest.writeString(this.iMobileExtension);
            dest.writeString(this.vMobileno);
            dest.writeString(this.vVerificationcode);
            dest.writeString(this.iLoginType);
            dest.writeString(this.eUserType);
            dest.writeString(this.eStatus);
            dest.writeString(this.iIsDelete);
            dest.writeString(this.iIsApprove);
            dest.writeString(this.iCreatedAt);
            dest.writeString(this.iUpdatedAt);
            dest.writeString(this.iLastlogin);
            dest.writeString(this.iCompletedStep);
            dest.writeString(this.iIsOnline);
        }

        public ResponseData() {
        }

        protected ResponseData(Parcel in) {
            this.iUserId = in.readString();
            this.txAuthToken = in.readString();
            this.vUserName = in.readParcelable(Object.class.getClassLoader());
            this.vEmail = in.readParcelable(Object.class.getClassLoader());
            this.dBirthdate = in.readParcelable(Object.class.getClassLoader());
            this.eGender = in.readParcelable(Object.class.getClassLoader());
            this.txAbout = in.readParcelable(Object.class.getClassLoader());
            this.txAddress1 = in.readParcelable(Object.class.getClassLoader());
            this.txAddress2 = in.readParcelable(Object.class.getClassLoader());
            this.vCity = in.readParcelable(Object.class.getClassLoader());
            this.vState = in.readParcelable(Object.class.getClassLoader());
            this.vZipcode = in.readString();
            this.txProfilePic = in.readParcelable(Object.class.getClassLoader());
            this.vPassword = in.readParcelable(Object.class.getClassLoader());
            this.iMobileExtension = in.readString();
            this.vMobileno = in.readString();
            this.vVerificationcode = in.readString();
            this.dLatitude = in.readParcelable(Object.class.getClassLoader());
            this.dLongitude = in.readParcelable(Object.class.getClassLoader());
            this.txFacebookId = in.readParcelable(Object.class.getClassLoader());
            this.txGoogleId = in.readParcelable(Object.class.getClassLoader());
            this.iLoginType = in.readString();
            this.eUserType = in.readString();
            this.eStatus = in.readString();
            this.iIsDelete = in.readString();
            this.iIsApprove = in.readString();
            this.iCreatedAt = in.readString();
            this.iUpdatedAt = in.readString();
            this.iLastlogin = in.readString();
            this.iCompletedStep = in.readString();
            this.iIsOnline = in.readString();
        }

        public final Parcelable.Creator<ResponseData> CREATOR = new Parcelable.Creator<ResponseData>() {
            @Override
            public ResponseData createFromParcel(Parcel source) {
                return new ResponseData(source);
            }

            @Override
            public ResponseData[] newArray(int size) {
                return new ResponseData[size];
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
        dest.writeParcelable(this.responseData, flags);
        dest.writeString(this.responseMessage);
    }

    public SignUpModel() {
    }

    protected SignUpModel(Parcel in) {
        this.responseCode = (Integer) in.readValue(Integer.class.getClassLoader());
        this.responseData = in.readParcelable(ResponseData.class.getClassLoader());
        this.responseMessage = in.readString();
    }

    public static final Parcelable.Creator<SignUpModel> CREATOR = new Parcelable.Creator<SignUpModel>() {
        @Override
        public SignUpModel createFromParcel(Parcel source) {
            return new SignUpModel(source);
        }

        @Override
        public SignUpModel[] newArray(int size) {
            return new SignUpModel[size];
        }
    };
}
