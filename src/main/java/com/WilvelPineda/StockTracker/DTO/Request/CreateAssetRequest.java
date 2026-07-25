package com.WilvelPineda.StockTracker.DTO.Request;

import com.WilvelPineda.StockTracker.Model.AssetType;

public record CreateAssetRequest(String symbol,
                                 String name,
                                 AssetType type,
                                 String marketId) {
}
