package com.angelherrarte.lab12

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.angelherrarte.lab12.ui.theme.Lab12Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab12Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyResponsiveUI()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyResponsiveUI() {
    val scrollState = rememberScrollState()
    val configuration = LocalConfiguration.current
    val screenWidth = LocalDensity.current.run { configuration.screenWidthDp.dp }
    val screenHeight = LocalDensity.current.run { configuration.screenHeightDp.dp }
    val orientation = configuration.orientation

    val columnModifier = if (orientation == Configuration.ORIENTATION_PORTRAIT) {
        Modifier
            .fillMaxHeight()
            .padding(16.dp)
    } else {
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    }

    Column(
        modifier = columnModifier,
        horizontalAlignment = if (screenWidth < 600.dp) Alignment.CenterHorizontally else Alignment.Start
    ) {
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Escribe algo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {},
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Botón")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.example_image),
            contentDescription = "Imagen de ejemplo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(80.dp).align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            LazyColumn {
                items(20) { index ->
                    ListItemView("Item $index")
                }
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.fillMaxHeight()
            ) {
                items(20) { index ->
                    ListItemView("Grid Item $index")
                }
            }
        }
    }
}

@Composable
fun ListItemView(text: String) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Text(text = text)
        }
    }
}