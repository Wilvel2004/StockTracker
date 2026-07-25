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
                request.type(),
                request.marketId()
        );

        Asset savedAsset = assetRepository.save(asset);

        return new AssetResponse(
                savedAsset.getId(),
                savedAsset.getSymbol(),
                savedAsset.getName(),
                savedAsset.getType(),
                savedAsset.getMarketId()
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
                            asset.getType(),
                            asset.getMarketId()
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

    public AssetResponse getAssetById(Long id) {

        Asset asset = assetRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Asset not found with id: " + id
                        )
                );


        return new AssetResponse(
                asset.getId(),
                asset.getSymbol(),
                asset.getName(),
                asset.getType(),
                asset.getMarketId()
        );
    }

    public void deleteAsset(Long id) {

        if (!assetRepository.existsById(id)) {

            throw new IllegalArgumentException(
                    "Asset not found with id: " + id
            );
        }


        assetRepository.deleteById(id);
    }
}