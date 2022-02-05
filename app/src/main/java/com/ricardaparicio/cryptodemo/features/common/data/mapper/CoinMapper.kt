package com.ricardaparicio.cryptodemo.features.common.data.mapper

import com.ricardaparicio.cryptodemo.features.common.data.model.CoinApiModel
import com.ricardaparicio.cryptodemo.features.common.data.model.CoinSummaryApiModel
import com.ricardaparicio.cryptodemo.features.common.domain.model.Coin
import com.ricardaparicio.cryptodemo.features.common.domain.model.CoinSummary
import javax.inject.Inject

class CoinMapper @Inject constructor() {

    fun mapCoinSummary(coinSummaryApiModel: CoinSummaryApiModel): CoinSummary =
        CoinSummary(
            id = coinSummaryApiModel.id,
            symbol = coinSummaryApiModel.symbol,
            name = coinSummaryApiModel.name,
            image = coinSummaryApiModel.image,
            price = coinSummaryApiModel.current_price
        )

    fun mapCoin(coinApiModel: CoinApiModel): Coin =
        Coin(
            coinSummary = CoinSummary(
                id = coinApiModel.id,
                symbol = coinApiModel.symbol,
                name = coinApiModel.name,
                image = coinApiModel.image.large,
                price = coinApiModel.market_data.current_price.eur,
            ),
            description = coinApiModel.description.es,
            ath = coinApiModel.market_data.ath.eur,
            marketCap = coinApiModel.market_data.market_cap.eur,
            priceChange24h = coinApiModel.market_data.price_change_24h,
            priceChangePercentage24h = coinApiModel.market_data.price_change_percentage_24h,
        )
}