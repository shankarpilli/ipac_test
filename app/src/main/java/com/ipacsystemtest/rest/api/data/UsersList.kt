package com.ipacsystemtest.rest.api.data

import com.google.gson.annotations.SerializedName

data class UsersList(
    @SerializedName("results") val results: ArrayList<User>
) : java.io.Serializable {
    data class User(
        @SerializedName("gender") val gender: String?,
        @SerializedName("name") val name: Name,
        @SerializedName("picture") val picture: Picture,
        @SerializedName("location") val location: Location?
    ) : java.io.Serializable

    data class Name(
        @SerializedName("title") val title: String,
        @SerializedName("first") val first: String,
        @SerializedName("last") val last: String
    ) : java.io.Serializable

    data class Picture(
        @SerializedName("large") val large: String,
        @SerializedName("medium") val medium: String,
        @SerializedName("thumbnail") val thumbnail: String
    ) : java.io.Serializable

    data class Location(
        @SerializedName("city") val city: String?,
        @SerializedName("state") val state: String?,
        @SerializedName("coordinates") val coordinates: Coordinates?
    ) : java.io.Serializable

    data class Coordinates(
        @SerializedName("latitude") val latitude: String?,
        @SerializedName("longitude") val longitude: String?
    ) : java.io.Serializable
}
