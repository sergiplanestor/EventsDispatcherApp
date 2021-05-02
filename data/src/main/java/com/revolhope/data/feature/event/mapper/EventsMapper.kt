package com.revolhope.data.feature.event.mapper

import com.revolhope.data.feature.event.response.EventNetworkResponse
import com.revolhope.data.feature.event.response.EventsNetworkResponse
import com.revolhope.domain.feature.event.model.EventModel
import com.revolhope.domain.feature.event.model.EventType

object EventsMapper {

    fun fromEventsNetworkResponseToModel(response: EventsNetworkResponse): List<EventModel> =
        response.events?.map { it.let(::fromEventNetworkResponseToModel) } ?: emptyList()

    private fun fromEventNetworkResponseToModel(response: EventNetworkResponse): EventModel =
        EventModel(
            timestamp = response.timestamp ?: Long.MIN_VALUE,
            userId = response.userId ?: Int.MIN_VALUE,
            type = EventType.fromApi(response.type)
        )

}
