package io.github.justincodinguk.core.ext

import java.text.DecimalFormat

fun Int.truncatedToString(): String {
    val df = DecimalFormat("#.#")
    return when {
        this > 999999 -> "${df.format(this/1000000.0f)}m"
        this > 999  -> "${df.format(this/1000.0f)}k"
        else -> toString()
    }
}