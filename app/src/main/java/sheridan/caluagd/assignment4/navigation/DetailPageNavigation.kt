package sheridan.caluagd.assignment4.navigation

object DetailPageNavigation : NavigationDestination{
    override val route: String = "DetailPage"
    override val titleRes: String = "Details Page"
    const val MARS_ID_ARG = "marsId"
    val routeWithArgs = "$route/{$MARS_ID_ARG}"

}