package com.ramazanpeker.cryptocurrency.presentation.coin_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.ramazanpeker.cryptocurrency.domain.model.Coin
import com.ramazanpeker.cryptocurrency.presentation.coin_detail.components.CoinTag
import com.ramazanpeker.cryptocurrency.presentation.coin_detail.components.TeamListItem
import com.ramazanpeker.cryptocurrency.presentation.coin_list.components.CoinListItem


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState(initial = CoinDetailState.Loading)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        when (state) {
            is CoinDetailState.Success -> {
                val data = (state as CoinDetailState.Success).data
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(20.dp)
                ) {
                    item {
                        CoinListItem(
                            coin = Coin(
                                data?.coinId,
                                data?.isActive,
                                data?.isNew,
                                data?.name,
                                data?.rank,
                                data?.symbol
                            ), onItemClick = {})
                        Spacer(modifier = Modifier.height(15.dp))
                        data?.description?.let {
                            Text(
                                text = it,
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = "Tags",
                            color = Color.White,
                            style = MaterialTheme.typography.headlineLarge
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        FlowRow(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            data?.tags?.forEach { tag ->
                                if (tag != null) {
                                    CoinTag(tag = tag)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = "Team members",
                            color = Color.White,
                            style = MaterialTheme.typography.headlineLarge
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                    items(data?.team ?: emptyList()) { teamMember ->
                        TeamListItem(
                            teamMember = teamMember,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        )


                        Divider()
                    }
                }
            }

            is CoinDetailState.Error -> {
                val st = state as CoinDetailState.Error
                st.errorMessage?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }

            }

            CoinDetailState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

            }

        }
    }
}