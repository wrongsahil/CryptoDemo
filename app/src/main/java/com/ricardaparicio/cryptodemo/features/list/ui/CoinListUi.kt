package com.ricardaparicio.cryptodemo.features.list.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.ricardaparicio.cryptodemo.core.util.TypedBlock
import com.ricardaparicio.cryptodemo.features.common.ui.model.model.CoinSummaryUiModel
import com.ricardaparicio.cryptodemo.features.list.ui.viewmodel.CoinListViewModel

@Composable
fun CoinListScreen(onClickCoin: TypedBlock<CoinSummaryUiModel>) {
    val viewModel = hiltViewModel<CoinListViewModel>()
    CoinList(viewModel.uiState, onClickCoin)
}

@Composable
private fun CoinList(uiState: CoinListUiState, onClickCoin: TypedBlock<CoinSummaryUiModel>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 30.dp, vertical = 30.dp)
    ) {
        val size = uiState.coins.size
        items(size) { index ->
            val coinItem = uiState.coins[index]
            CoinItem(coinItem, onClickCoin)
            if (index < size - 1) {
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}

@Composable
private fun CoinItem(coinItem: CoinSummaryUiModel, onClickCoin: TypedBlock<CoinSummaryUiModel>) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClickCoin(coinItem) }
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier,
                textAlign = TextAlign.Center,
                text = coinItem.marketCapPosition,
                style = MaterialTheme.typography.caption,
            )
            Spacer(Modifier.width(20.dp))
            Image(
                modifier = Modifier.size(40.dp),
                painter = rememberImagePainter(
                    data = coinItem.image,
                    builder = {
                        transformations(CircleCropTransformation())
                        crossfade(true)
                    }
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Spacer(Modifier.width(20.dp))
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = coinItem.symbol,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = coinItem.price,
                    style = MaterialTheme.typography.h4
                )
            }

        }
    }
}