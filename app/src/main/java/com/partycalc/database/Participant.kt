package com.partycalc.database

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Participant : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var name: String
    var size: Int

    constructor(name: String, size: Int) {
        this.name = name
        this.size = size
    }

    constructor(`in`: Parcel) {
        id = `in`.readInt()
        name = `in`.readString()
        size = `in`.readInt()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(name)
        dest.writeInt(size)
    }

    companion object {
        val CREATOR: Creator<Participant?> = object : Creator<Participant?> {
            override fun createFromParcel(`in`: Parcel): Participant? {
                return Participant(`in`)
            }

            override fun newArray(size: Int): Array<Participant?> {
                return arrayOfNulls(size)
            }
        }
    }
}