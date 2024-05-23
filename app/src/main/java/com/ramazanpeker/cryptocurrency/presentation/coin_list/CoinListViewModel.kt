package com.ramazanpeker.cryptocurrency.presentation.coin_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramazanpeker.cryptocurrency.common.Resource
import com.ramazanpeker.cryptocurrency.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = MutableSharedFlow<CoinListState>()
    val state = _state.asSharedFlow()

    init {
        getCoins()  
    }
    private fun getCoins() {
        viewModelScope.launch {
            getCoinsUseCase.invoke().collect { state ->
                val result = when (state) {
                    is Resource.Success -> CoinListState.Success(state.data ?: emptyList())
                    is Resource.Loading -> CoinListState.Loading
                    is Resource.Error -> CoinListState.Error(state.message ?: "unexpected error ") 
                }
                _state.emit(result)
            }
        }
    }
}