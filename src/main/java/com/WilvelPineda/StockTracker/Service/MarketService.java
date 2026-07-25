package com.WilvelPineda.StockTracker.Service;

import com.WilvelPineda.StockTracker.Client.FinnhubClient;
import com.WilvelPineda.StockTracker.DTO.Response.MarketAssetResponse;
import com.WilvelPineda.StockTracker.DTO.Response.QuoteResponse;
import com.WilvelPineda.StockTracker.Entity.Asset;
import com.WilvelPineda.StockTracker.Model.AssetType;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MarketService {

    private final FinnhubClient finnhubClient;

    public MarketService(FinnhubClient finnhubClient) {
        this.finnhubClient = finnhubClient;
    }

    public MarketAssetResponse getMarketData(Asset asset)
            throws IOException, InterruptedException {

        if (asset.getType() != AssetType.STOCK) {
            throw new IllegalArgumentException( "Cannot get price for asset: " + asset.getSymbol());
        }

        QuoteResponse quote = finnhubClient.getQuote(asset.getSymbol());

        return new MarketAssetResponse(
                asset.getId(),
                asset.getSymbol(),
                asset.getName(),
                asset.getType(),
                quote.currentPrice()
        );
    }
}