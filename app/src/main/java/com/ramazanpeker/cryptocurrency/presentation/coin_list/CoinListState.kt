package com.ramazanpeker.cryptocurrency.presentation.coin_list

import com.ramazanpeker.cryptocurrency.domain.model.Coin

sealed class CoinListState {
    data class Success(val data: List<Coin>? = emptyList()) : CoinListState()
    data object Loading : CoinListState()
    data class Error(val errorMessage: String? = " ") : CoinListState()
}
