package com.ramazanpeker.cryptocurrency.data.remote

import com.ramazanpeker.cryptocurrency.data.remote.dto.CoinDetailDto
import com.ramazanpeker.cryptocurrency.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getByCoinId(@Path("coinId") coinId: String): CoinDetailDto
}