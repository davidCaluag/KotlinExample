package sheridan.caluagd.assignment4

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import sheridan.caluagd.assignment4.navigation.MarsNavigationHost

@Composable
fun AppRootScreen(navController: NavHostController = rememberNavController()){
    MarsNavigationHost(navController = navController)
}