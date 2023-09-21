package com.praveen.spacexapp.network.resources

import org.json.JSONObject
import retrofit2.Response
import java.io.IOException

fun <T> handleHttpError(response: Response<T>):ErrorResponse {
    return when(response.code())  {
        400 -> {
            // Invalid Request
            val errorBodySrc = response.errorBody()?.source()?.buffer?.snapshot()?.utf8()
            var errorMessage = "Bad Request (400): Oops something went wrong. Please try again."
            errorBodySrc?.let {
                val errorBodyJsonObject = JSONObject(it)
                errorMessage = errorBodyJsonObject.getString("message")
            }
       ErrorResponse(errorMessage,BadRequestException)
        }
        401 -> {
            // UnAuthorised User
            val errorMessage = "UnAuthorized(401): Oops something went wrong. Please try again."
            ErrorResponse(errorMessage,UnAuthorizedException)
        }
        404 -> {
            // UnAuthorised User
            val errorMessage = "Not found(404): URL Not Found."
            ErrorResponse(errorMessage,UnKnownException)
        }
        500 -> {
            // Internal Server Error
            val errorMessage = "InternalServerError(500): Server Problem"
            ErrorResponse(errorMessage,InternalServerException)
        }
        else -> {
            val errorMessage = "UnKnown(${response.code()}): Undefined Error"
            ErrorResponse(errorMessage,UnKnownException)
        }
    }
}

fun handleApiFailure(throwable: Throwable): ErrorResponse {
    return when (throwable) {
        is IOException -> {
            // IO exception
           ErrorResponse(throwable.message?:"",UnKnownException)
        } else -> {
            // UnKnown error
            ErrorResponse(throwable.message?:"",UnKnownException)

        }
    }
}