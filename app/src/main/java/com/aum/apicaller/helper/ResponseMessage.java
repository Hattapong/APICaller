package com.aum.apicaller.helper;

import android.os.Parcel;
import android.os.Parcelable;

public class  ResponseMessage implements Parcelable {

    private int statusCode;

    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.statusCode);
        dest.writeString(this.message);
    }

    public ResponseMessage() {
    }

    protected ResponseMessage(Parcel in) {
        this.statusCode = in.readInt();
        this.message = in.readString();
    }

    public static final Parcelable.Creator<ResponseMessage> CREATOR = new Parcelable.Creator<ResponseMessage>() {
        @Override
        public ResponseMessage createFromParcel(Parcel source) {
            return new ResponseMessage(source);
        }

        @Override
        public ResponseMessage[] newArray(int size) {
            return new ResponseMessage[size];
        }
    };
}
