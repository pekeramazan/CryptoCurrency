package com.ramazanpeker.cryptocurrency.presentation.coin_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramazanpeker.cryptocurrency.common.Resource
import com.ramazanpeker.cryptocurrency.domain.model.Coin
import com.ramazanpeker.cryptocurrency.domain.use_case.get_coins.GetCoinUseCase
import com.ramazanpeker.cryptocurrency.domain.use_case.get_coins.GetCoinsUseCase
import com.ramazanpeker.cryptocurrency.presentation.coin_detail.CoinDetailState
import com.ramazanpeker.cryptocurrency.presentation.coin_detail.CoinDetailViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale.filter
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<CoinListState>(CoinListState.Loading)
    val state = _state.asStateFlow()

    var searchQuery = mutableStateOf("")
    private var allCoins: List<Coin> = emptyList()

    init {
        getCoins()  
    }
    private fun getCoins() {
        viewModelScope.launch {
            getCoinsUseCase.invoke().collect { state ->
                val result = when (state) {
                    is Resource.Success -> {
                        allCoins = state.data ?: emptyList()
                        CoinListState.Success(state.data ?: emptyList())
                    }
                    is Resource.Loading -> CoinListState.Loading
                    is Resource.Error -> CoinListState.Error(state.message ?: "unexpected error ") 
                }
                _state.emit(result)
            }
        }
    }

    fun onSearch(query: String) {
            searchQuery.value = query
            filterCoins()

    }

    private fun filterCoins() {
        val filteredList = if (searchQuery.value.isEmpty()) {
            allCoins
        } else if (searchQuery.value.length >= 3) {
            allCoins.filter { coin ->
                coin.name?.contains(searchQuery.value, ignoreCase = true) == true ||
                        coin.id?.contains(searchQuery.value, ignoreCase = true) == true
            }
        } else {
            allCoins
        }
        _state.value = CoinListState.Success(filteredList)
    }

}