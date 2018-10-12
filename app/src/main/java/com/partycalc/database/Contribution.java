package com.partycalc.database;

import android.os.Parcel;
import android.os.Parcelable;

public class Contribution implements Parcelable {

    String contrib_comment;
    Float contrib_amount;

    public Contribution(String contrib_comment, Float contrib_amount) {
        this.contrib_comment = contrib_comment;
        this.contrib_amount = contrib_amount;
    }

    protected Contribution(Parcel in) {
        contrib_comment = in.readString();
        if (in.readByte() == 0) {
            contrib_amount = null;
        } else {
            contrib_amount = in.readFloat();
        }
    }

    public static final Creator<Contribution> CREATOR = new Creator<Contribution>() {
        @Override
        public Contribution createFromParcel(Parcel in) {
            return new Contribution(in);
        }

        @Override
        public Contribution[] newArray(int size) {
            return new Contribution[size];
        }
    };

    public String getContrib_comment() {
        return contrib_comment;
    }

    public void setContrib_comment(String contrib_comment) {
        this.contrib_comment = contrib_comment;
    }

    public Float getContrib_amount() {
        return contrib_amount;
    }

    public void setContrib_amount(Float contrib_amount) {
        this.contrib_amount = contrib_amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(contrib_comment);
        if (contrib_amount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(contrib_amount);
        }
    }
}
