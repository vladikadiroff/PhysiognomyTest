package ru.vladikadiroff.physiognomy.extensions

import androidx.lifecycle.MutableLiveData

//Set default value for any type of MutableLiveData
fun <T: Any?> MutableLiveData<T>.default(initialValue: T) = apply { value = initialValue }
//Set new value for any type of MutableLiveData
fun <T> MutableLiveData<T>.set(newValue: T) = apply { value = newValue }