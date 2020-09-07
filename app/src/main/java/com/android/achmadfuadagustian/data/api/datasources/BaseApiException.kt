package com.android.achmadfuadagustian.data.api.datasources

import com.android.achmadfuadagustian.data.api.datasources.dto.ApiDto
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import com.framework.exceptions.DataStreamException


class BaseApiException(message: String?, var exception: HttpException? = null) : DataStreamException(message, exception) {
    private val summary: Summary?
        get() {
            var sum: Summary? = null
            exception?.let { httpException ->
                try {
                    httpException.response()?.errorBody()?.let { errorBody ->
                        val dto = Gson().fromJson<ApiDto<Any>>(errorBody.string(), ApiDto::class.java)
                        dto?.let {
                            sum = Summary(dto)
                            return sum
                        }
                    }
                    httpException.response()?.body()?.let { body ->
                        val dto = body as ApiDto<Any>
                        dto.let {
                            sum = Summary(dto)
                            return sum
                        }
                    }
                } catch (jse: JsonSyntaxException) {

                } catch (e: Exception) {

                }
            }
            return sum
        }

    var apiCode: String? = null
        get() = summary?.code ?: field
    var apiMessage: String? = null
        get() = summary?.message ?: field

    class Summary(baseApiDto: ApiDto<Any>) {
        var code: String? = baseApiDto.statusCode
        var message: String? = baseApiDto.message
    }
}