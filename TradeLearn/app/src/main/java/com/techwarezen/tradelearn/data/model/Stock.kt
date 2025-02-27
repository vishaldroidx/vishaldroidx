package com.techwarezen.tradelearn.data.model

/**
 * Data class representing a stock
 *
 * @param symbol The stock symbol (e.g., AAPL)
 * @param name The company name (e.g., Apple Inc.)
 * @param price The current stock price
 * @param changePercent The percentage change in price
 */
data class Stock(
    val symbol: String,
    val name: String,
    val price: Double,
    val changePercent: Double
)