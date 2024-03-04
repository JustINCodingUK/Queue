package io.github.justincodinguk.core.ext

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.timeDiffString(): String {
    val currentTime = System.currentTimeMillis()
    val timeDiff = (currentTime - time)/1000

    return when {
        timeDiff >= 60*60*24*365 -> "${timeDiff.floorDiv(60*60*24*365)}y ago"
        timeDiff >= 60*60*24*12 -> "${timeDiff.floorDiv(60*60*24*12)}m ago"
        timeDiff >= 60*60*24 -> "${timeDiff.floorDiv(60*60*24)}d ago"
        timeDiff >= 60*60 ->"${timeDiff.floorDiv(60*60)}h ago"
        timeDiff >= 60 -> "${timeDiff.floorDiv(60)}min ago"
        else -> "${timeDiff}s ago"
    }
}

fun Date.toSimpleDateString() : String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
    return simpleDateFormat.format(this)
}