package sheridan.caluagd.assignment4.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dagger.hilt.android.lifecycle.HiltViewModel
import sheridan.caluagd.assignment4.database.MarsPhotosRepository
import sheridan.caluagd.assignment4.ui.theme.home.HomeScreen
import sheridan.caluagd.assignment4.ui.theme.home.MarsUiState
import sheridan.caluagd.assignment4.ui.theme.home.MarsViewModel

@Composable

fun MarsNavigationHost(
    navController : NavHostController,
    modifier: Modifier = Modifier,
    repository: MarsPhotosRepository
){
    NavHost(navController = navController, modifier = Modifier, startDestination = MainPageNavigation.route){
        val marsViewModel: MarsViewModel = MarsViewModel(repository)


        composable(route = MainPageNavigation.route){
            HomeScreen(
                viewModel = marsViewModel,
                marsUiState = marsViewModel.marsUiState,
                retryAction = marsViewModel::getMarsPhotos,
                contentPadding = PaddingValues(2.dp),
                click = { id->
                    navController.navigate("${DetailPageNavigation.route}/${id}")
                }
            )

        }

        composable(
            route = DetailPageNavigation.routeWithArgs,
            arguments = listOf(navArgument(DetailPageNavigation.MARS_ID_ARG) {
                type = NavType.IntType
            })
        ) {
            val viewModel = HiltViewModel()
            DetailPageScreen(
                navigateToEditProduct = { navController.navigate("${DetailPageNavigation.route}/$it") },
                navigateBack = { navController.popBackStack() },
                viewModel = viewModel
            )
        }


    }

}