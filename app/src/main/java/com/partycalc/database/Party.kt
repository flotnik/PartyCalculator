package com.partycalc.database

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*

@Entity
class Party : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var name: String

    @TypeConverters(DateConverter::class)
    var date: Date

    constructor(name: String, date: Date) {
        this.name = name
        this.date = date
    }

    constructor(`in`: Parcel) {
        id = `in`.readInt()
        name = `in`.readString()
        date = `in`.readValue(ClassLoader.getSystemClassLoader()) as Date
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(name)
        dest.writeValue(date)
    }

    companion object {
        val CREATOR: Creator<Party> = object : Creator<Party> {
            override fun createFromParcel(`in`: Parcel): Party? {
                return Party(`in`)
            }

            override fun newArray(size: Int): Array<Party?> {
                return arrayOfNulls(size)
            }
        }
    }
}