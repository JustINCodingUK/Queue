package io.github.justincodinguk.features.posts

data class PostsRouteState(
    val mode: PostsMode,
    val status: PostsStatus
) {
    companion object {
        fun initialState(): PostsRouteState {
            return PostsRouteState(
                PostsMode.ALL,
                PostsStatus.LOADING
            )
        }
    }
}