package br.com.lucena.recyclerview.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Pull implements Parcelable {

    int id;

    public String title;

    @SerializedName("created_at")
    public Date date;

    public String body;

    @SerializedName("user")
    public Autor user;

    @SerializedName("html_url")
    public String url;

    protected Pull(Parcel in) {
        id = in.readInt();
        title = in.readString();
        body = in.readString();
        user = in.readParcelable(Autor.class.getClassLoader());
        url = in.readString();
    }

    public static final Creator<Pull> CREATOR = new Creator<Pull>() {
        @Override
        public Pull createFromParcel(Parcel in) {
            return new Pull(in);
        }

        @Override
        public Pull[] newArray(int size) {
            return new Pull[size];
        }
    };

    @Override
    public String toString() {
        return user.login + " - " + user.avatar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(body);
        parcel.writeParcelable(user, i);
        parcel.writeString(url);
    }
}
