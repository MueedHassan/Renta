package com.example.home.tenants.module.ui.Home.domain
//@Serializable
//data class Property(
//    @SerialName("Area")
//    val area: String = "",
//    @SerialName("Bedroom")
//    val bedroom: String = "",
//    @SerialName("City")
//    val city: String = "",
//    @SerialName("Description")
//    val description: String = "",
//    @SerialName("List Of Images Uris")
//    val listOfImagesUris: List<String> = emptyList(),
//    @SerialName("Location")
//    val location: String = "",
//    @SerialName("MaxOccupancy")
//    val maxOccupancy: String = "",
//    @SerialName("PlaceType")
//    val placeType: String? = null,
//    @SerialName("Price")
//    val price: String = "",
//    @SerialName("PropertyStatus")
//    val propertyStatus: String = "",
//    @SerialName("RenterStatus")
//    val renterStatus: String = "",
//    @SerialName("Title")
//    val title: String = "",
//    @SerialName("Washroom")
//    val washroom: String = "",
//    @SerialName("ownerId")
//    val ownerId: String = "",
//    @SerialName("phoneNumber")
//    val phoneNumber: String = "",
//    @SerialName("timestamp")
//    @Contextual
//    val timestamp: Timestamp? = null
//)
//import android.os.Parcel
//import android.os.Parcelable
//import com.google.firebase.Timestamp
//import kotlinx.serialization.Contextual
//import kotlinx.serialization.SerialName
//import kotlinx.serialization.Serializable
//
//@Serializable
//data class Property(
//    @SerialName("Area")
//    val area: String = "",
//    @SerialName("Bedroom")
//    val bedroom: String = "",
//    @SerialName("City")
//    val city: String = "",
//    @SerialName("Description")
//    val description: String = "",
//    @SerialName("List Of Images Uris")
//    val listOfImagesUris: List<String> = emptyList(),
//    @SerialName("Location")
//    val location: String = "",
//    @SerialName("MaxOccupancy")
//    val maxOccupancy: String = "",
//    @SerialName("PlaceType")
//    val placeType: String? = null,
//    @SerialName("Price")
//    val price: String = "",
//    @SerialName("PropertyStatus")
//    val propertyStatus: String = "",
//    @SerialName("RenterStatus")
//    val renterStatus: String = "",
//    @SerialName("Title")
//    val title: String = "",
//    @SerialName("Washroom")
//    val washroom: String = "",
//    @SerialName("ownerId")
//    val ownerId: String = "",
//    @SerialName("phoneNumber")
//    val phoneNumber: String = "",
//    @SerialName("timestamp")
//    @Contextual
//    val timestamp: Timestamp? = null
//) : Parcelable {
//    constructor(parcel: Parcel) : this(
//        area = parcel.readString() ?: "",
//        bedroom = parcel.readString() ?: "",
//        city = parcel.readString() ?: "",
//        description = parcel.readString() ?: "",
//        listOfImagesUris = parcel.createStringArrayList() ?: emptyList(),
//        location = parcel.readString() ?: "",
//        maxOccupancy = parcel.readString() ?: "",
//        placeType = parcel.readString(),
//        price = parcel.readString() ?: "",
//        propertyStatus = parcel.readString() ?: "",
//        renterStatus = parcel.readString() ?: "",
//        title = parcel.readString() ?: "",
//        washroom = parcel.readString() ?: "",
//        ownerId = parcel.readString() ?: "",
//        phoneNumber = parcel.readString() ?: "",
//        timestamp = parcel.readParcelable(Timestamp::class.java.classLoader)
//    )
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(area)
//        parcel.writeString(bedroom)
//        parcel.writeString(city)
//        parcel.writeString(description)
//        parcel.writeStringList(listOfImagesUris)
//        parcel.writeString(location)
//        parcel.writeString(maxOccupancy)
//        parcel.writeString(placeType)
//        parcel.writeString(price)
//        parcel.writeString(propertyStatus)
//        parcel.writeString(renterStatus)
//        parcel.writeString(title)
//        parcel.writeString(washroom)
//        parcel.writeString(ownerId)
//        parcel.writeString(phoneNumber)
//        parcel.writeParcelable(timestamp, flags)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<Property> {
//        override fun createFromParcel(parcel: Parcel): Property {
//            return Property(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Property?> {
//            return arrayOfNulls(size)
//        }
//    }
//}
import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
@Parcelize
data class Property(
    val Area: String = "",
    val Bedroom: String = "",
    val City: String = "",
    val Descrition: String = "",
    val List_Of_Images_Uris: List<String> = emptyList(), // Adjusted field name
    val Location: String = "",
    val MaxOccupancy: String = "",
    val PlaceType: String = "",
    val Price: String = "",
    val PropertyStatus: String = "",
    val RenterStatus: String = "",
    val Title: String = "",
    val Washroom: String = "",
    val ownerId: String = "",
    val phoneNumber: String = "",
    val timestamp:Timestamp? = null
) : Parcelable {
    companion object : Parceler<Property> {
        override fun Property.write(parcel: Parcel, flags: Int) {
            parcel.writeString(Area)
            parcel.writeString(Bedroom)
            parcel.writeString(City)
            parcel.writeString(Descrition)
            parcel.writeStringList(List_Of_Images_Uris) // Adjusted field name
            parcel.writeString(Location)
            parcel.writeString(MaxOccupancy)
            parcel.writeString(PlaceType)
            parcel.writeString(Price)
            parcel.writeString(PropertyStatus)
            parcel.writeString(RenterStatus)
            parcel.writeString(Title)
            parcel.writeString(Washroom)
            parcel.writeString(ownerId)
            parcel.writeString(phoneNumber)
            parcel.writeParcelable(timestamp, flags)
        }

        override fun create(parcel: Parcel): Property {
            return Property(
                Area = parcel.readString() ?: "",
                Bedroom = parcel.readString() ?: "",
                City = parcel.readString() ?: "",
                Descrition = parcel.readString() ?: "",
                List_Of_Images_Uris = parcel.createStringArrayList() ?: emptyList(), // Adjusted field name
                Location = parcel.readString() ?: "",
                MaxOccupancy = parcel.readString() ?: "",
                PlaceType = parcel.readString() ?: "",
                Price = parcel.readString() ?: "",
                PropertyStatus = parcel.readString() ?: "",
                RenterStatus = parcel.readString() ?: "",
                Title = parcel.readString() ?: "",
                Washroom = parcel.readString() ?: "",
                ownerId = parcel.readString() ?: "",
                phoneNumber = parcel.readString() ?: "",
                timestamp = parcel.readParcelable(Timestamp::class.java.classLoader)

            )
        }
    }
}
