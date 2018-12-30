package com.kstransfter.models.app;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by SONI on 12/30/2018.
 */

public class GetPages implements Parcelable {

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

        @SerializedName("iPageId")
        @Expose
        private Integer iPageId;
        @SerializedName("vTitle")
        @Expose
        private String vTitle;
        @SerializedName("ltxDescription")
        @Expose
        private String ltxDescription;

        public Integer getIPageId() {
            return iPageId;
        }

        public void setIPageId(Integer iPageId) {
            this.iPageId = iPageId;
        }

        public String getVTitle() {
            return vTitle;
        }

        public void setVTitle(String vTitle) {
            this.vTitle = vTitle;
        }

        public String getLtxDescription() {
            return ltxDescription;
        }

        public void setLtxDescription(String ltxDescription) {
            this.ltxDescription = ltxDescription;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(this.iPageId);
            dest.writeString(this.vTitle);
            dest.writeString(this.ltxDescription);
        }

        public ResponseDatum() {
        }

        protected ResponseDatum(Parcel in) {
            this.iPageId = (Integer) in.readValue(Integer.class.getClassLoader());
            this.vTitle = in.readString();
            this.ltxDescription = in.readString();
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

    public GetPages() {
    }

    protected GetPages(Parcel in) {
        this.responseCode = (Integer) in.readValue(Integer.class.getClassLoader());
        this.responseMessage = in.readString();
        this.responseData = in.createTypedArrayList(ResponseDatum.CREATOR);
    }

    public static final Parcelable.Creator<GetPages> CREATOR = new Parcelable.Creator<GetPages>() {
        @Override
        public GetPages createFromParcel(Parcel source) {
            return new GetPages(source);
        }

        @Override
        public GetPages[] newArray(int size) {
            return new GetPages[size];
        }
    };
}
