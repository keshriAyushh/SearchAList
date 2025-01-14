package com.ayush.searchalist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayush.playground.model.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(FlowPreview::class)
class SearchViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    private val searchResults = _uiState
        .debounce(500L)
        .map { it.searchText }
        .map {
            if (it.isEmpty()) {
                Person.dummy()
            } else {
                Person.dummy().filter { person ->
                    matchesCriteria(person, it)
                }
            }
        }
        .onEach { _uiState.update { it.copy(isLoading = false) } }

    val uiState = combine(
        _uiState,
        searchResults
    ) { state, filteredPersons -> state.copy(persons = filteredPersons) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(3000),
            initialValue = UiState(persons = Person.dummy())
        )

    fun onEvent(event: UiEvent) {
        when (event) {
            is UiEvent.OnSearchTextChange -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _uiState.update { it.copy(searchText = event.text, isLoading = true) }
                }
            }
        }
    }

    private fun matchesCriteria(person: Person, searchText: String): Boolean {
        if (searchText.contains(" ")) {
            val terms = searchText.split(" ")
            return terms.all { term ->
                matchesSingleTerm(person, term)
            }
        }
        return matchesSingleTerm(person, searchText)
    }

    private fun matchesSingleTerm(person: Person, term: String): Boolean {
        if (person.firstName.contains(term, ignoreCase = true) ||
            person.lastName.contains(term, ignoreCase = true)
        ) {
            return true
        }

        val initials =
            "${person.firstName.firstOrNull() ?: ""}${person.lastName.firstOrNull() ?: ""}"
        if (initials.equals(term, ignoreCase = true)) {
            return true
        }

        if (term.length == 2) {
            val firstChar = term[0]
            val secondChar = term[1]
            if (person.firstName.firstOrNull()?.equals(firstChar, ignoreCase = true) == true &&
                person.lastName.firstOrNull()?.equals(secondChar, ignoreCase = true) == true
            ) {
                return true
            }
        }
        return false
    }
}

data class UiState(
    val searchText: String = "",
    val persons: List<Person> = Person.dummy(),
    val isLoading: Boolean = false
)

sealed interface UiEvent {
    data class OnSearchTextChange(val text: String) : UiEvent
}