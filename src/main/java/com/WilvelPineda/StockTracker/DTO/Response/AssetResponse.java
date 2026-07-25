package com.WilvelPineda.StockTracker.DTO.Response;

import com.WilvelPineda.StockTracker.Model.AssetType;


public record AssetResponse(

        Long id,
        String symbol,
        String name,
        AssetType type

) {
}