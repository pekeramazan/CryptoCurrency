package com.ramazanpeker.cryptocurrency.presentation.coin_detail
 
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramazanpeker.cryptocurrency.common.Constants
import com.ramazanpeker.cryptocurrency.common.Resource
import com.ramazanpeker.cryptocurrency.domain.use_case.get_coins.GetCoinUseCase
import com.ramazanpeker.cryptocurrency.presentation.coin_list.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
     savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableSharedFlow<CoinDetailState>()
    val state = _state.asSharedFlow()


    init {
        savedStateHandle.get<String>(Constants.COIN_ID)?.let {
            getCoinById(it)
        }
    }

    private fun getCoinById(coinId: String) {
        viewModelScope.launch {
            getCoinUseCase.invoke(coinId = coinId).collect { state ->
                val result = when (state) {
                    is Resource.Success -> CoinDetailState.Success(state.data)
                    is Resource.Loading -> CoinDetailState.Loading
                    is Resource.Error -> CoinDetailState.Error(state.message ?: "unexpected error ")
                }
                _state.emit(result)
            }
        }
    }
}