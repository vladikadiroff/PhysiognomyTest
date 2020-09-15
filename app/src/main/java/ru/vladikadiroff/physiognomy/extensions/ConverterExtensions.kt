package ru.vladikadiroff.physiognomy.extensions

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.convertToString(data: Any?): String{
    return if(data != null) {
        when (data) {
            is String -> data
            is @StringRes Int -> getString(data)
            else -> data.toString()
        }
    } else {
        "This object is Null"
    }
}



