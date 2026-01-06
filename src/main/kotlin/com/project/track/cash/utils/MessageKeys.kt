package com.project.track.cash.utils

object MessageKeys {
    const val HTTP_4XX_400_BAD_REQUEST = "http.4xx.400-bad-request"
    const val HTTP_4XX_401_UNAUTHORIZED = "http.4xx.401-unauthorized"
    const val HTTP_4XX_403_FORBIDDEN = "http.4xx.403-forbidden"
    const val HTTP_4XX_404_NOT_FOUND = "http.4xx.404-not-found"
    const val HTTP_4XX_409_CONFLICT = "http.4xx.409-conflict"
    const val HTTP_5XX_500_INTERNAL_SERVER_ERROR = "http.5xx.500-internal-server-error"

    const val APPLICATION_VALIDATION_CONSTRAINTS_MESSAGE = "application.validation.constraints.details"
    const val APPLICATION_VALIDATION_CONSTRAINTS_NOTBLANK_FIELD_MESSAGE =
        "application.validation.constraints.not-blank.field.message"
    const val APPLICATION_VALIDATION_CONSTRAINTS_SIZE_FIELD_MESSAGE =
        "application.validation.constraints.size.field.message"
    const val APPLICATION_VALIDATION_CONSTRAINTS_EMAIL_MESSAGE =
        "application.validation.constraints.email.field.message"
    const val APPLICATION_VALIDATION_CONSTRAINTS_NOTBLANK_ENUM_DETAILS =
        "application.validation.constraints.not-blank.enum.message"
}