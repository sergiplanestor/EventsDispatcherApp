package com.revolhope.data.feature.event.datasource

import com.revolhope.domain.feature.event.model.EventModel
import com.revolhope.domain.feature.event.request.EventRequest

object EventCacheDataSource {

    private val events: MutableList<EventModel> = mutableListOf()

    var maxCapacity: Int = 50
    val isEmpty: Boolean get() = events.isEmpty()

    fun insertEvents(events: List<EventModel>) {
        this.events.clear()
        this.events.addAll(events)
    }

    fun fetch(request: EventRequest): List<EventModel> =
        events.filter {
            it.type == request.type && it.userId == request.userId
        }.sortedWith(compareBy { it.timestamp })

    fun dispatchEvent(event: EventModel): Boolean {
        if (events.count() + 1 < maxCapacity) {
            events.minByOrNull { it.timestamp }?.let {
                events.removeAt(events.indexOf(it))
            }
        }
        events.add(event)
        return true
    }
}
