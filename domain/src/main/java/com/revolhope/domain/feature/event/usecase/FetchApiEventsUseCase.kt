package com.revolhope.domain.feature.event.usecase

import com.revolhope.domain.common.model.State
import com.revolhope.domain.feature.event.model.EventModel
import com.revolhope.domain.feature.event.repository.EventRepository
import com.revolhope.domain.feature.event.request.EventApiRequest
import javax.inject.Inject

class FetchApiEventsUseCase @Inject constructor(
    private val eventRepository: EventRepository
) {

    suspend operator fun invoke(params: Params): State<List<EventModel>> =
        eventRepository.fetchApiEvents(params.request)

    data class Params(val request: EventApiRequest)
}
