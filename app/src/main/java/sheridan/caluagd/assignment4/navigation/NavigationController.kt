package sheridan.caluagd.assignment4.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import sheridan.caluagd.assignment4.ui.theme.detail.DetailsScreen
import sheridan.caluagd.assignment4.ui.theme.detail.MarsDetailViewModel
import sheridan.caluagd.assignment4.ui.theme.home.HomeScreen
import sheridan.caluagd.assignment4.ui.theme.home.MarsViewModel

@Composable
fun MarsNavigationHost(
    navController : NavHostController,
){
    NavHost(
        navController = navController,
        modifier = Modifier,
        startDestination = MainPageNavigation.route)
    {
        composable(route = MainPageNavigation.route){
            val marsViewModel: MarsViewModel = hiltViewModel()
            HomeScreen(
                viewModel = marsViewModel,
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
            val viewModel: MarsDetailViewModel = hiltViewModel()
            DetailsScreen(
                viewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }


    }

}