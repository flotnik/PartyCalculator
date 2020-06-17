package com.partycalc.database

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

class Contribution : Parcelable {
    var contrib_comment: String
    var contrib_amount: Float? = null

    constructor(contrib_comment: String, contrib_amount: Float?) {
        this.contrib_comment = contrib_comment
        this.contrib_amount = contrib_amount
    }

    protected constructor(`in`: Parcel) {
        contrib_comment = `in`.readString()
        contrib_amount = if (`in`.readByte().toInt() == 0) {
            null
        } else {
            `in`.readFloat()
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(contrib_comment)
        if (contrib_amount == null) {
            dest.writeByte(0.toByte())
        } else {
            dest.writeByte(1.toByte())
            dest.writeFloat(contrib_amount!!)
        }
    }

    companion object {
        val CREATOR: Creator<Contribution?> = object : Creator<Contribution?> {
            override fun createFromParcel(`in`: Parcel): Contribution? {
                return Contribution(`in`)
            }

            override fun newArray(size: Int): Array<Contribution?> {
                return arrayOfNulls(size)
            }
        }
    }
}