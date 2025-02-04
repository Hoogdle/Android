package com.example.jetpackcompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


// send ScreenEvent to the UI
class LaunchedEffectViewModel: ViewModel() {

    // sharedFlow : simply send events.
    private val _sharedFlow = MutableSharedFlow<ScreenEvents>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    // send the Event to UI(Screen)
    init{
        viewModelScope.launch {
            _sharedFlow.emit(ScreenEvents.ShowSnackbar("Hello World!"))
        }
    }

    // ScreenEvents
    sealed class ScreenEvents{
        data class ShowSnackbar(val message: String): ScreenEvents()
        data class Navigate(val route: String): ScreenEvents()
    }
}