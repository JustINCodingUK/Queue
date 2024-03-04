package io.github.justincodinguk.core.model

data class SearchQuery(
    val inTitle: String = "",
    val tags: List<String> = listOf(),
    val byUserId: Int = -1,
    val collective: String = "",
    val answerCount: Int = -1,
    val score: Int = Int.MIN_VALUE,
    val isAccepted: Boolean = false
) {
    companion object {
        fun emptySearchQuery() : SearchQuery {
            return SearchQuery()
        }
    }
}
