package com.revolhope.presentation.feature.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.revolhope.domain.feature.event.model.EventModel
import com.revolhope.domain.feature.event.request.EventApiRequest
import com.revolhope.domain.feature.event.request.EventRequest
import com.revolhope.domain.feature.event.usecase.DispatchEventUseCase
import com.revolhope.domain.feature.event.usecase.FetchApiEventsUseCase
import com.revolhope.domain.feature.event.usecase.FetchEventsUseCase
import com.revolhope.domain.feature.event.usecase.SetEventMaxCapacityUseCase
import com.revolhope.presentation.common.BaseViewModel
import com.revolhope.presentation.common.launchAsync
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val setEventMaxCapacityUseCase: SetEventMaxCapacityUseCase,
    private val fetchEventsUseCase: FetchEventsUseCase,
    private val fetchApiEventsUseCase: FetchApiEventsUseCase,
    private val dispatchEventUseCase: DispatchEventUseCase
) : BaseViewModel() {

    private val _maxCapacityLiveData = MutableLiveData<Boolean>()
    val maxCapacityLiveData: LiveData<Boolean> get() = _maxCapacityLiveData

    private val _fetchEventsLiveData = MutableLiveData<List<EventModel>>()
    val fetchEventsLiveData: LiveData<List<EventModel>> get() = _fetchEventsLiveData

    private val _dispatchEventLiveData = MutableLiveData<Boolean>()
    val dispatchEventLiveData: LiveData<Boolean> get() = _dispatchEventLiveData

    fun setMaxCapacity(capacity: Int) {
        launchAsync(
            asyncTask = {
                setEventMaxCapacityUseCase.invoke(SetEventMaxCapacityUseCase.Params(capacity))
            },
            onCompleteTask = { state ->
                handleState(
                    state = state,
                    onSuccess = _maxCapacityLiveData::setValue
                )
            }
        )
    }

    fun fetchApiEvents(request: EventApiRequest) {
        launchAsync(
            asyncTask = {
                fetchApiEventsUseCase.invoke(FetchApiEventsUseCase.Params(request))
            },
            onCompleteTask = { state ->
                handleState(
                    state = state,
                    onSuccess = _fetchEventsLiveData::setValue
                )
            }
        )
    }

    fun fetchEvents(request: EventRequest) {
        launchAsync(
            asyncTask = {
                fetchEventsUseCase.invoke(FetchEventsUseCase.Params(request))
            },
            onCompleteTask = { state ->
                handleState(
                    state = state,
                    onSuccess = _fetchEventsLiveData::setValue
                )
            }
        )
    }

    fun dispatchEvent(event: EventModel) {
        launchAsync(
            asyncTask = {
                dispatchEventUseCase.invoke(DispatchEventUseCase.Params(event))
            },
            onCompleteTask = { state ->
                handleState(
                    state = state,
                    onSuccess = _dispatchEventLiveData::setValue
                )
            }
        )
    }
}
