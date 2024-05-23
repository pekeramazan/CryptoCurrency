package com.ramazanpeker.cryptocurrency.data.repository

import com.ramazanpeker.cryptocurrency.data.remote.CoinPaprikaApi
import com.ramazanpeker.cryptocurrency.data.remote.dto.CoinDetailDto
import com.ramazanpeker.cryptocurrency.data.remote.dto.CoinDto
import com.ramazanpeker.cryptocurrency.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(private val coinPaprikaApi: CoinPaprikaApi) :
    CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return coinPaprikaApi.getCoins()
    }

    override suspend fun getByCoinId(coinId: String): CoinDetailDto {
        return coinPaprikaApi.getByCoinId(coinId = coinId)
    }
}