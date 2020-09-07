package com.framework.webapi

import android.content.Context

class WebApi private constructor(builder: Builder) : WebApiSpec, WebApiConnection {

    override val baseUrl: String
    override val version: String
    override val connectTimeout: Long
    override val readTimeout: Long
    override val enableLogging: Boolean
    val enableHttpInspector: Boolean
    val context: Context?

    init {
        this.baseUrl = builder.baseUrl
        this.version = builder.version
        this.connectTimeout = builder.connectTimeout
        this.readTimeout = builder.readTimeout
        this.enableLogging = builder.enableLogging
        this.enableHttpInspector = builder.enableHttpInspector
        this.context = builder.context
    }

    class Builder(val baseUrl: String, val version: String) {

        var connectTimeout: Long = 30
            private set
        var readTimeout: Long = 30
            private set
        var enableLogging: Boolean = false
            private set
        var context: Context? = null
            private set
        var enableHttpInspector: Boolean = false
            private set

        fun setConnectTimeout(timeout: Long): Builder {
            this.connectTimeout = timeout
            return this
        }

        fun setReadTimeout(timeout: Long): Builder {
            this.readTimeout = timeout
            return this
        }

        fun setLoggingEnabled(enableLogging: Boolean): Builder {
            this.enableLogging = enableLogging
            return this
        }

        fun setHttpInspector(context: Context, showNotification: Boolean): Builder {
            this.enableHttpInspector = showNotification
            this.context = context
            return this
        }

        fun build(): WebApi {
            return WebApi(this)
        }
    }
}