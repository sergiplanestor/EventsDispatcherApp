package com.revolhope.data.feature.event.response

import com.google.gson.annotations.SerializedName

data class EventNetworkResponse(
    @SerializedName("timestamp") val timestamp: Long?,
    @SerializedName("user_id") val userId: Int?,
    @SerializedName("type") val type: String?
)
