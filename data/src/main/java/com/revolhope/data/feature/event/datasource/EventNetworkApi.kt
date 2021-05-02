package com.revolhope.data.feature.event.datasource

import com.revolhope.data.feature.event.response.EventsNetworkResponse
import retrofit2.http.GET

interface EventNetworkApi {

    @GET
    suspend fun fetchEvents(url: String): EventsNetworkResponse

}
