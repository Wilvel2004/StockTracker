package com.WilvelPineda.StockTracker.DTO;

public record StockResponse(String symbol,
                            QuoteResponse quote) {
}
