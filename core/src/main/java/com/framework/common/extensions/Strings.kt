package com.framework.common.extensions

import kotlin.math.abs

fun changeNumberFormat(number:Int) : String {
    return when {
        abs(number/1000000) > 1 -> {
            (number / 1000000).toString() + "m"
        }
        abs(number / 1000) > 1 -> {
            (number / 1000).toString() + "k"
        }
        else -> {
            number.toString()
        }
    }
}
