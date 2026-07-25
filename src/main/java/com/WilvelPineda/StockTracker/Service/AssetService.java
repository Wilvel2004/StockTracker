package com.WilvelPineda.StockTracker.Service;

import com.WilvelPineda.StockTracker.DTO.Request.CreateAssetRequest;
import com.WilvelPineda.StockTracker.DTO.Response.AssetResponse;
import com.WilvelPineda.StockTracker.DTO.Response.MarketAssetResponse;
import com.WilvelPineda.StockTracker.Entity.Asset;
import com.WilvelPineda.StockTracker.repository.AssetRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssetService {

    private final AssetRepository assetRepository;
    private final MarketService marketService;


    public AssetService(
            AssetRepository assetRepository,
            MarketService marketService
    ) {
        this.assetRepository = assetRepository;
        this.marketService = marketService;
    }


    public AssetResponse createAsset(CreateAssetRequest request) {

        Asset asset = new Asset(
                request.symbol(),
                request.name(),
                request.type()
        );

        Asset savedAsset = assetRepository.save(asset);

        return new AssetResponse(
                savedAsset.getId(),
                savedAsset.getSymbol(),
                savedAsset.getName(),
                savedAsset.getType()
        );
    }


    public List<AssetResponse> getAssets() {

        List<Asset> assets = assetRepository.findAll();

        List<AssetResponse> responses = new ArrayList<>();

        for (Asset asset : assets) {

            responses.add(
                    new AssetResponse(
                            asset.getId(),
                            asset.getSymbol(),
                            asset.getName(),
                            asset.getType()
                    )
            );
        }

        return responses;
    }


    public List<MarketAssetResponse> getMarketAssets()
            throws IOException, InterruptedException {

        List<Asset> assets = assetRepository.findAll();

        List<MarketAssetResponse> responses = new ArrayList<>();

        for (Asset asset : assets) {

            responses.add(
                    marketService.getMarketData(asset)
            );
        }

        return responses;
    }
}