package com.WilvelPineda.StockTracker.DTO.Response;

public record CryptoPriceResponse(
        double price,
        double changePercent
) {

}