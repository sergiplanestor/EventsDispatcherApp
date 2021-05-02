package com.revolhope.domain.feature.event.repository

import com.revolhope.domain.common.model.State
import com.revolhope.domain.feature.event.model.EventModel
import com.revolhope.domain.feature.event.request.EventApiRequest
import com.revolhope.domain.feature.event.request.EventRequest

interface EventRepository {

    suspend fun setEventsMaxCapacity(maxCapacity: Int): State<Boolean>

    suspend fun fetchApiEvents(request: EventApiRequest = EventApiRequest.EVENT_50): State<List<EventModel>>

    suspend fun fetchEvents(request: EventRequest): State<List<EventModel>>

    suspend fun dispatchEvent(event: EventModel): State<Boolean>
}
