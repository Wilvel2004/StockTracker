package com.WilvelPineda.StockTracker.Service.Price;

import com.WilvelPineda.StockTracker.Client.CoinGeckoClient;
import com.WilvelPineda.StockTracker.DTO.Response.CryptoPriceResponse;
import com.WilvelPineda.StockTracker.DTO.Response.MarketAssetResponse;
import com.WilvelPineda.StockTracker.Entity.Asset;
import com.WilvelPineda.StockTracker.Model.AssetType;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CryptoPriceProvider implements PriceProvider {


    private final CoinGeckoClient coinGeckoClient;


    public CryptoPriceProvider(CoinGeckoClient coinGeckoClient) {
        this.coinGeckoClient = coinGeckoClient;
    }


    @Override
    public boolean supports(Asset asset) {

        return asset.getType() == AssetType.CRYPTO;

    }


    @Override
    public MarketAssetResponse getPrice(Asset asset)
            throws IOException, InterruptedException {


        CryptoPriceResponse crypto =
                coinGeckoClient.getPrice(
                        asset.getMarketId()
                );


        return new MarketAssetResponse(
                asset.getId(),
                asset.getSymbol(),
                asset.getName(),
                asset.getType(),
                crypto.price(),
                crypto.changePercent()
        );
    }
}