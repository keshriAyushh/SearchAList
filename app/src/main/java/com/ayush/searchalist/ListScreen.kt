package com.ayush.searchalist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ayush.playground.model.Person
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListScreen() {
    val viewModel = koinViewModel<SearchViewModel>()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var text by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(all = 16.dp)
    ) {
        SearchTextComponent(
            textValue = text,
            modifier = Modifier.fillMaxWidth(),
            isLoading = uiState.isLoading
        ) { newText ->
            viewModel.onEvent(UiEvent.OnSearchTextChange(newText))
            text = newText
        }

        Spacer(modifier = Modifier.height(10.dp))

        PersonList(
            modifier = Modifier.weight(1f),
            persons = uiState.persons
        )
    }
}

@Composable
private fun ListItem(person: Person) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "${person.firstName} ${person.lastName}",
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
private fun PersonList(
    modifier: Modifier = Modifier,
    persons: List<Person>,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(persons) { person ->
            ListItem(person)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
private fun SearchTextComponent(
    textValue: String,
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    onTextChange: (String) -> Unit
) {
    TextField(
        value = textValue,
        onValueChange = {
            onTextChange.invoke(it)
        },
        placeholder = {
            Text(text = "Search")
        },
        modifier = modifier,
        trailingIcon = {
            if(isLoading) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp))
            } else {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search",
                    tint = Color.White
                )
            }
        },
        textStyle = TextStyle(
            fontSize = 16.sp,
            color = Color.White
        )
    )
}