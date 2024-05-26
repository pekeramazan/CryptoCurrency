package com.ramazanpeker.cryptocurrency.presentation.coin_list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ramazanpeker.cryptocurrency.presentation.Screen
import com.ramazanpeker.cryptocurrency.presentation.coin_list.components.CoinListItem

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState(initial = CoinListState.Loading)

    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is CoinListState.Success -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items((state as CoinListState.Success).data ?: emptyList()) { coin ->
                        CoinListItem(coin = coin,
                            onItemClick = {
                                navController.navigate(Screen.CoinDetailScreen.route + "/${coin.id}")
                            })

                    }

                }
            }

            is CoinListState.Error -> {
                val st = state as CoinListState.Error
                if (st.errorMessage?.isNotBlank() == true) {
                    Text(
                        text = st.errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )

                }
            }

            CoinListState.Loading -> {

                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }

    }

}
