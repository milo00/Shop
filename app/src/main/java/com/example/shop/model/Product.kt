package com.example.shop.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Product(
    @StringRes val titleResourceId: Int,
    @StringRes val capacityResourceId: Int,
    @StringRes val descriptionResourceId: Int,
    @StringRes val prizeResourceId: Int,
    val colorResourceId: String?,
    @DrawableRes val imageResourceId: Int,
    var favorite: Boolean = false,
    var quantityInCart: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readBoolean(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(titleResourceId)
        parcel.writeInt(capacityResourceId)
        parcel.writeInt(descriptionResourceId)
        parcel.writeInt(prizeResourceId)
        parcel.writeString(colorResourceId)
        parcel.writeInt(imageResourceId)
        parcel.writeBoolean(favorite)
        parcel.writeInt(quantityInCart)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}