package com.revolhope.domain.feature.event.request

import android.os.Parcelable
import com.revolhope.domain.feature.event.model.EventType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventRequest(
    val userId: Int,
    val type: EventType
): Parcelable
