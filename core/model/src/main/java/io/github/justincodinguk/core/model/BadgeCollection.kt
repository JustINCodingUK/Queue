package io.github.justincodinguk.core.model

import kotlinx.serialization.Serializable

@Serializable
data class BadgeCollection(
    val bronze: Int = 0,
    val silver: Int = 0,
    val gold: Int = 0
)
