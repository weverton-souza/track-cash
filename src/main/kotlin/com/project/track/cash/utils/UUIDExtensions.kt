package com.project.track.cash.utils

import java.util.UUID
import java.util.regex.Pattern

fun UUID.toPlainString(): String = toString().replace("-", "")

fun UUID.toFormattedString(uuid: String): String = uuid
    .replaceFirst(
        "(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)".toRegex(),
        "$1-$2-$3-$4-$5"
    )

fun UUID.isValidFormat(uuid: String): Boolean {
    val pattern = Pattern.compile(
        "(\\p{XDigit}{8})-(\\p{XDigit}{4})-(\\p{XDigit}{4})-(\\p{XDigit}{4})-(\\p{XDigit}+)"
    )
    return pattern.matcher(uuid).matches()
}

fun UUID.fromPlainString(plainUuid: String): UUID {
    require(plainUuid.length == 32) { "Plain UUID string must be 32 characters long" }
    return UUID.fromString(toFormattedString(plainUuid))
}