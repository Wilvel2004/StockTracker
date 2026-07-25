package com.WilvelPineda.StockTracker.Service;

import com.WilvelPineda.StockTracker.DTO.Response.MarketAssetResponse;
import com.WilvelPineda.StockTracker.Entity.Asset;
import com.WilvelPineda.StockTracker.Service.Price.PriceProvider;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MarketService {


    private final List<PriceProvider> providers;


    public MarketService(List<PriceProvider> providers) {
        this.providers = providers;
    }


    public MarketAssetResponse getMarketData(Asset asset)
            throws IOException, InterruptedException {


        return providers.stream()
                .filter(provider -> provider.supports(asset))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "No provider found for "
                                        + asset.getSymbol()
                        )
                )
                .getPrice(asset);

    }
}