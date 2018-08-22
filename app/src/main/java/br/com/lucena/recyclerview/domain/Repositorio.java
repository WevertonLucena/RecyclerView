package br.com.lucena.recyclerview.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wtonl on 18/08/2018.
 */

public class Repositorio implements Parcelable {

    @SerializedName("id")
    int id;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("stargazers_count")
    public int stars;

    @SerializedName("forks")
    public int forks;

    @SerializedName("owner")
    public Autor autor;

    protected Repositorio(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        stars = in.readInt();
        forks = in.readInt();
    }

    public static final Creator<Repositorio> CREATOR = new Creator<Repositorio>() {
        @Override
        public Repositorio createFromParcel(Parcel in) {
            return new Repositorio(in);
        }

        @Override
        public Repositorio[] newArray(int size) {
            return new Repositorio[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeInt(stars);
        parcel.writeInt(forks);
    }
}
