package com.WilvelPineda.StockTracker.DTO;

public record StockResponse(String symbol,
                            double currentPrice,
                            double highPrice,
                            double lowPrice,
                            double openPrice,
                            double previousClose) {
}
