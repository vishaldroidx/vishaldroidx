package com.techwarezen.tradelearn.data.model

/**
 * Data class representing a stock transaction
 *
 * @param symbol The stock symbol (e.g., AAPL)
 * @param type The transaction type (Buy or Sell)
 * @param quantity The number of shares
 * @param price The price per share at the time of transaction
 * @param timestamp The timestamp of the transaction
 */
data class Transaction(
    val symbol: String,
    val type: String,
    val quantity: Int,
    val price: Double,
    val timestamp: Long
) {
    /**
     * Calculate the total value of the transaction
     */
    val total: Double
        get() = quantity * price
}