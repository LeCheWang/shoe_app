package com.android.demo1;

import android.os.Parcel;
import android.os.Parcelable;

public class Account implements Parcelable {
    private String username;
    private String address;
    private int age;

    public Account(String username, String address, int age) {
        this.username = username;
        this.address = address;
        this.age = age;
    }

    public Account() {
    }

    protected Account(Parcel in) {
        username = in.readString();
        address = in.readString();
        age = in.readInt();
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeString(address);
        parcel.writeString(age+"");
    }
}
