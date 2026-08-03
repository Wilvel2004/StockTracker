package com.WilvelPineda.StockTracker.DTO.Response;

import com.WilvelPineda.StockTracker.Model.AssetType;

public record MarketAssetResponse(
        Long id,
        String symbol,
        String name,
        AssetType type,
        double currentPrice,
        double changePercent
) {

}