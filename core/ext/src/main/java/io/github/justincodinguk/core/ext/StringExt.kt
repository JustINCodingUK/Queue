package io.github.justincodinguk.core.ext

import androidx.core.text.HtmlCompat
import io.github.justincodinguk.core.model.SearchQuery

fun String.toPlainText() = HtmlCompat
    .fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
    .toString()

// TODO: Pattern matching, I DON'T KNOW JUST DO IT SOMEHOW
fun String.toSearchQuery(): SearchQuery {
        val tags = Regex("\\[([A-Za-z;]*)]")
                    .find(this)?.groupValues ?: listOf()

        val userId = Regex("user:([0-9]*)")
                .find(this)?.groupValues?.get(0)?.toInt() ?: -1


        val collective = Regex("collective:([A-Za-z]*)")
                .find(this)?.groupValues?.get(0) ?: ""

        val answers = Regex("answers:(-?[0-9])")
    return SearchQuery.emptySearchQuery()
}
