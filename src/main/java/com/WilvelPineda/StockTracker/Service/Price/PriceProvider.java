package com.WilvelPineda.StockTracker.Service.Price;

import com.WilvelPineda.StockTracker.DTO.Response.MarketAssetResponse;
import com.WilvelPineda.StockTracker.Entity.Asset;

import java.io.IOException;

public interface PriceProvider {
    boolean supports(Asset asset);

    MarketAssetResponse getPrice(Asset asset)
            throws IOException, InterruptedException;
}
