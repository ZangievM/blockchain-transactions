package com.zm.data_.model.api.profile


import com.google.gson.annotations.SerializedName

data class Profile(
    @SerializedName("account_id")
    val accountId: String?,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("guest_score")
    val guestScore: Any?,
    @SerializedName("is_partner")
    val isPartner: Boolean?,
    @SerializedName("joined_at")
    val joinedAt: String?,
    @SerializedName("langs_spoken_names")
    val langsSpokenNames: List<Any>?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("online_status")
    val onlineStatus: String?,
    @SerializedName("phone_country")
    val phoneCountry: Any?,
    @SerializedName("phone_number")
    val phoneNumber: Any?,
    @SerializedName("profile_id")
    val profileId: String?,
    @SerializedName("profile_type")
    val profileType: String?,
    @SerializedName("reviews_count")
    val reviewsCount: Int?
)