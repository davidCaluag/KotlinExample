package sheridan.caluagd.assignment4.ui.theme.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import sheridan.caluagd.assignment4.model.Mars


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    viewModel: MarsDetailViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state: State<DetailsUiState> = viewModel.detailsUiState.collectAsState()
    val detailsUiState: DetailsUiState = state.value

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { DetailListTopBar("Detail Screen" ,onBack, scrollBehavior) }
    ) { innerPadding ->
        if (detailsUiState is DetailsUiState.Success) {
            DetailsBody(
                mars = detailsUiState.mars,
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            )
        }
    }
}

@Composable
private fun DetailsBody(
    mars: Mars, modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        RestaurantDetails(
            title = "#${mars.id.toString()}'s Photo",
            imgSrc = mars.imgSrc,
            modifier = Modifier.padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
    }
}


@Composable
fun RestaurantDetails(
    title: String,
    imgSrc: String,
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
) {
    Column(
        horizontalAlignment = horizontalAlignment, modifier = modifier
    ) {
        Text(
            text = title, style = MaterialTheme.typography.headlineSmall
        )
        AsyncImage(
            model = coil.request.ImageRequest.Builder(context = LocalContext.current).data(imgSrc)
                .crossfade(true).build(),
            error = painterResource(sheridan.caluagd.assignment4.R.drawable.ic_broken_image),
            placeholder = painterResource(sheridan.caluagd.assignment4.R.drawable.loading_img),
            contentDescription = stringResource(sheridan.caluagd.assignment4.R.string.mars_photo),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DetailListTopBar(
    title : String,
    onBack: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) = CenterAlignedTopAppBar(
    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        titleContentColor = MaterialTheme.colorScheme.primary,
    ),
    actions = {
        IconButton(
            onClick = onBack,
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Refresh"
            )
        }
    },
    title = {
        Text(
            title,
            style = MaterialTheme.typography.headlineMedium
        )
    },
    scrollBehavior = scrollBehavior
)