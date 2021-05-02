package com.revolhope.data.feature.event.repositoryimpl

import com.revolhope.data.common.BaseRepositoryImpl
import com.revolhope.data.feature.event.datasource.EventCacheDataSource
import com.revolhope.data.feature.event.datasource.EventNetworkDataSource
import com.revolhope.data.feature.event.mapper.EventsMapper
import com.revolhope.domain.common.model.State
import com.revolhope.domain.feature.event.model.EventModel
import com.revolhope.domain.feature.event.repository.EventRepository
import com.revolhope.domain.feature.event.request.EventApiRequest
import com.revolhope.domain.feature.event.request.EventRequest
import java.lang.Exception
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val networkDataSource: EventNetworkDataSource
) : EventRepository, BaseRepositoryImpl() {

    override suspend fun setEventsMaxCapacity(maxCapacity: Int): State<Boolean> =
        launchStateful {
            EventCacheDataSource.maxCapacity = maxCapacity
            true
        }

    override suspend fun fetchApiEvents(request: EventApiRequest): State<List<EventModel>> =
        launchStateful {
            networkDataSource.fetchEvents(request.path)
                .let(EventsMapper::fromEventsNetworkResponseToModel)
                .also(EventCacheDataSource::insertEvents)
        }

    override suspend fun fetchEvents(request: EventRequest): State<List<EventModel>> =
        launchStateful {
            if (EventCacheDataSource.isEmpty) {
                val apiState = fetchApiEvents()
                if (apiState is State.Error) {
                    throw apiState.throwable ?: Exception(apiState.message)
                }
            }
            EventCacheDataSource.fetch(request)
        }

    override suspend fun dispatchEvent(event: EventModel): State<Boolean> =
        launchStateful {
            EventCacheDataSource.dispatchEvent(event)
        }
}
