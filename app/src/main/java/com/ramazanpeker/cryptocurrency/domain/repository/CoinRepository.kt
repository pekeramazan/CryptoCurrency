package com.ramazanpeker.cryptocurrency.domain.repository

import com.ramazanpeker.cryptocurrency.data.remote.dto.CoinDetailDto
import com.ramazanpeker.cryptocurrency.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun  getByCoinId(coinId: String): CoinDetailDto
}