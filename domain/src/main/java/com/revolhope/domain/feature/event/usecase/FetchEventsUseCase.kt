package com.revolhope.domain.feature.event.usecase

import com.revolhope.domain.common.model.State
import com.revolhope.domain.feature.event.model.EventModel
import com.revolhope.domain.feature.event.repository.EventRepository
import com.revolhope.domain.feature.event.request.EventRequest
import javax.inject.Inject

class FetchEventsUseCase @Inject constructor(
    private val eventRepository: EventRepository
) {

    suspend operator fun invoke(params: Params): State<List<EventModel>> =
        eventRepository.fetchEvents(params.request)

    data class Params(val request: EventRequest)
}
