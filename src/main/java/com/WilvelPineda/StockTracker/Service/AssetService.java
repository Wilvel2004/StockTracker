package com.WilvelPineda.StockTracker.Service;

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


    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }


    public Asset saveAsset(Asset asset) {
        return assetRepository.save(asset);
    }
}
