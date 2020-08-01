package com.whjung.movieproject.view.base

open class  BaseEvent<T> {
    var type: Enum<*>? = null
    var data: T? = null
}