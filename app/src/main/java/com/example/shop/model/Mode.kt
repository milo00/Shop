package com.example.shop.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.StringRes

data class Mode (

    @StringRes val nameResourceId: Int,
    var chosen: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int){
        parcel.writeInt(nameResourceId)
        parcel.writeBoolean(chosen)
    }

    companion object CREATOR : Parcelable.Creator<Mode> {
        override fun createFromParcel(parcel: Parcel): Mode {
            return Mode(parcel)
        }

        override fun newArray(size: Int): Array<Mode?> {
            return arrayOfNulls(size)
        }
    }
}