package br.com.lucena.recyclerview.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wtonl on 18/08/2018.
 */

public class Autor implements Parcelable{

    @SerializedName("id")
    public int id;

    @SerializedName("login")
    public String login;

    @SerializedName("avatar_url")
    public String avatar;

    @Override
    public String toString() {
        return "[ " + id + ", " + login + "," + avatar + "]";
    }

    protected Autor(Parcel in) {
        id = in.readInt();
        login = in.readString();
        avatar = in.readString();
    }

    public static final Creator<Autor> CREATOR = new Creator<Autor>() {
        @Override
        public Autor createFromParcel(Parcel in) {
            return new Autor(in);
        }

        @Override
        public Autor[] newArray(int size) {
            return new Autor[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(login);
        parcel.writeString(avatar);
    }
}
