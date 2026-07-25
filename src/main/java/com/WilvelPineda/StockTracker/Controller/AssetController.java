package com.WilvelPineda.StockTracker.Controller;

import com.WilvelPineda.StockTracker.Entity.Asset;
import com.WilvelPineda.StockTracker.Service.AssetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    public List<Asset> getAssets() {

        return assetService.getAllAssets();

    }

    @PostMapping
    public Asset createAsset(
            @RequestBody Asset asset
    ) {

        return assetService.saveAsset(asset);

    }

}