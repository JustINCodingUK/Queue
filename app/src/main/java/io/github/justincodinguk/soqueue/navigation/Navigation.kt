package io.github.justincodinguk.soqueue.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.github.justincodinguk.features.details.DetailsRoute
import io.github.justincodinguk.features.posts.PostsRoute

@Composable
fun QueueNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.POSTS.name
    ) {
        composable(Routes.POSTS.name) {
            PostsRoute {
                navController.navigate("${Routes.DETAILS.name}/$it")
            }
        }

        composable(
            "${Routes.DETAILS.name}/{questionId}",
            arguments = listOf(navArgument("questionId") { type = NavType.IntType }),
            enterTransition = { expandIn(tween(500)) + scaleIn(tween(500)) },
            exitTransition = { shrinkOut(tween(500)) + scaleOut(tween(500)) }
        ) {
            DetailsRoute(questionId = it.arguments?.getInt("questionId") ?: 0)
        }
    }
}