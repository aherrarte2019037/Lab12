package com.angelherrarte.lab12

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
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

@Composable
fun MyResponsiveUI() {
    // Determina la orientación de la pantalla
    val orientation = LocalConfiguration.current.orientation

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        // Campos de texto
        var textFieldValue = remember { TextFieldValue("") }
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = { newValue -> textFieldValue = newValue },
            label = { Text("Escribe algo") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )

        // Botón
        Button(
            onClick = {},
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Presiona aquí")
        }

        // Imagen
        Image(
            painter = painterResource(id = R.drawable.my_image),
            contentDescription = "Descripción de la imagen",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(200.dp)
        )

        // Lista o Grid dependiendo de la orientación
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Lista vertical
            LazyColumn {
                items(items = listOf("Item 1", "Item 2", "Item 3")) { item ->
                    ListItem(item = item)
                }
            }
        } else {
            // Grid horizontal
            LazyGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(items = listOf("Item 1", "Item 2", "Item 3")) { item ->
                    ListItem(item = item)
                }
            }
        }
    }
}

@Composable
fun ListItem(item: String) {
    Card(modifier = Modifier.padding(8.dp).fillMaxWidth(), elevation = 2.dp) {
        Text(
            text = item,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyResponsiveUI()
}