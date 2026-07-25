package com.WilvelPineda.StockTracker.Service;

import com.WilvelPineda.StockTracker.DTO.Request.CreateAssetRequest;
import com.WilvelPineda.StockTracker.DTO.Response.AssetResponse;
import com.WilvelPineda.StockTracker.Entity.Asset;
import com.WilvelPineda.StockTracker.repository.AssetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
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
}
