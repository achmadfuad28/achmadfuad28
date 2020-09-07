package com.framework.webapi

interface WebApiConnection {

    val connectTimeout: Long
    val readTimeout: Long
    val enableLogging: Boolean
}
