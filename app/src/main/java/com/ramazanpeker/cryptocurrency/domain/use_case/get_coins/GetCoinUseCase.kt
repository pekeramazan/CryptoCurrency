package com.ramazanpeker.cryptocurrency.domain.use_case.get_coins

import com.ramazanpeker.cryptocurrency.common.Resource
import com.ramazanpeker.cryptocurrency.data.remote.dto.toCoinDetail
import com.ramazanpeker.cryptocurrency.domain.model.CoinDetail
import com.ramazanpeker.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(private val coinRepository: CoinRepository) {

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try { 
            emit(Resource.Loading())
            val coin = coinRepository.getByCoinId(coinId = coinId).toCoinDetail()
            emit(Resource.Success(coin))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An occured unexpected error"))

        } catch (e: IOException) {
            emit(Resource.Error("Could not reach server check your internet connection"))

        }
    }
}