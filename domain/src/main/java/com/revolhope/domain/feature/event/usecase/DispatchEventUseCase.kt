package com.revolhope.domain.feature.event.usecase

import com.revolhope.domain.common.model.State
import com.revolhope.domain.feature.event.model.EventModel
import com.revolhope.domain.feature.event.repository.EventRepository
import javax.inject.Inject

class DispatchEventUseCase @Inject constructor(
    private val eventRepository: EventRepository
) {

    suspend operator fun invoke(params: Params): State<Boolean> =
        eventRepository.dispatchEvent(params.event)

    data class Params(val event: EventModel)
}
