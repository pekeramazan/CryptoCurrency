package com.ramazanpeker.cryptocurrency.presentation.coin_detail

import com.ramazanpeker.cryptocurrency.domain.model.CoinDetail

sealed class CoinDetailState {
    data class Success(val data: CoinDetail?) : CoinDetailState()
    data object Loading : CoinDetailState()
    data class Error(val errorMessage: String?) : CoinDetailState()
}
