package com.example.shop.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes

data class Product(
    var titleResource: String?,
    var capacityResource: String?,
    var descriptionResource: String?,
    var prizeResource: String?,
    var categoryResourceId: Int,
    var colorResource: String?,
    @DrawableRes val imageResourceId: Int,
    var favorite: Boolean,
    var quantityInCart: Int
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readBoolean(),
        parcel.readInt(),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(titleResource)
        parcel.writeString(capacityResource)
        parcel.writeString(descriptionResource)
        parcel.writeString(prizeResource)
        parcel.writeInt(categoryResourceId)
        parcel.writeString(colorResource)
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