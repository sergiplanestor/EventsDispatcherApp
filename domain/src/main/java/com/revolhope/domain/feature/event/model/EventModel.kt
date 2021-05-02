package com.revolhope.domain.feature.event.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventModel(
    val timestamp: Long,
    val userId: Int,
    val type: EventType
) : Parcelable
