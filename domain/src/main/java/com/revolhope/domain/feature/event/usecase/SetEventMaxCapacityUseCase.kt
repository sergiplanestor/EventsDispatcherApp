package com.revolhope.domain.feature.event.usecase

import com.revolhope.domain.common.model.State
import com.revolhope.domain.feature.event.repository.EventRepository
import javax.inject.Inject

class SetEventMaxCapacityUseCase @Inject constructor(
    private val eventRepository: EventRepository
) {

    suspend operator fun invoke(params: Params): State<Boolean> =
        eventRepository.setEventsMaxCapacity(params.capacity)

    data class Params(val capacity: Int)
}
