package com.revolhope.data.feature.event.response

import com.google.gson.annotations.SerializedName

data class EventsNetworkResponse(
    @SerializedName("events") val events: List<EventNetworkResponse>?
)
