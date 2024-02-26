package com.example.testonlinestore.utils

interface EventHandler<T> {

    fun obtainEvent(event : T)

}