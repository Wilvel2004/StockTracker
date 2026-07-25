package com.WilvelPineda.StockTracker.Service.Price;

import com.WilvelPineda.StockTracker.Client.FinnhubClient;
import com.WilvelPineda.StockTracker.DTO.Response.MarketAssetResponse;
import com.WilvelPineda.StockTracker.DTO.Response.QuoteResponse;
import com.WilvelPineda.StockTracker.Entity.Asset;
import com.WilvelPineda.StockTracker.Model.AssetType;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StockPriceProvider implements PriceProvider {

    private final FinnhubClient finnhubClient;

    public StockPriceProvider(FinnhubClient finnhubClient) {
        this.finnhubClient = finnhubClient;
    }


    @Override
    public boolean supports(Asset asset) {

        return asset.getType() == AssetType.STOCK;

    }


    @Override
    public MarketAssetResponse getPrice(Asset asset)
            throws IOException, InterruptedException {


        QuoteResponse quote =
                finnhubClient.getQuote(asset.getMarketId());


        return new MarketAssetResponse(
                asset.getId(),
                asset.getSymbol(),
                asset.getName(),
                asset.getType(),
                quote.currentPrice()
        );
    }
}