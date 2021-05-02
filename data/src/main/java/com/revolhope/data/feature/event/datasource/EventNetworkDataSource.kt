package com.revolhope.data.feature.event.datasource

import com.revolhope.data.feature.event.response.EventsNetworkResponse
import com.revolhope.data.injection.RestModule
import javax.inject.Inject

class EventNetworkDataSource @Inject constructor(
    private val api: EventNetworkApi
) {

    companion object {
        const val EVENTS_BASE_URL = "https://hrcdn.net/s3_pub/istreet-assets/"
    }

    suspend fun fetchEvents(path: String): EventsNetworkResponse =
        api.fetchEvents(url = "${EVENTS_BASE_URL}${path}")

}
