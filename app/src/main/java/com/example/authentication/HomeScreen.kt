package com.example.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    var searchText by remember { mutableStateOf("") }
    val products = listOf("a","a","a","a")
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .background(color = Color.Green),
                title = {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                        shape = RoundedCornerShape(28.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp),
                            verticalAlignment =
                                Alignment.CenterVertically
                        ) {
                            Icon(Icons.Filled.Search, contentDescription = null)
                            Spacer(Modifier.width(8.dp))
                            BasicTextField(
                                value = searchText,
                                onValueChange = { searchText = it },
                                modifier = Modifier.weight(1f),
                                textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurfaceVariant),
                                decorationBox = { innerTextField ->
                                    if (searchText.isEmpty()) {
                                        Text(
                                            "search",
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    innerTextField()
                                }
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),

                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),

            ) {
                items (products){ product ->
                    Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFEFB8C8))


                    ) {
                            // product content
                        }
                }



            }
        }
    }
}