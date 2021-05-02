package com.revolhope.domain.feature.event.model

enum class EventType(val apiName: String) {
    LOCATION("location"),
    WEB("web"),
    APPLICATION("application"),
    UNKNOWN("");

    companion object {
        fun fromApi(apiName: String?): EventType =
            values().associateBy { it.apiName }[apiName] ?: UNKNOWN
    }
}
