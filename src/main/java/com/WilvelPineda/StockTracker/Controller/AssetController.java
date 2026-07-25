package com.WilvelPineda.StockTracker.Controller;

import com.WilvelPineda.StockTracker.DTO.Request.CreateAssetRequest;
import com.WilvelPineda.StockTracker.DTO.Response.AssetResponse;
import com.WilvelPineda.StockTracker.DTO.Response.MarketAssetResponse;
import com.WilvelPineda.StockTracker.Entity.Asset;
import com.WilvelPineda.StockTracker.Service.AssetService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    public List<AssetResponse> getAssets() {

        return assetService.getAssets();

    }

    @PostMapping
    public AssetResponse createAsset(@RequestBody CreateAssetRequest request) {
        return assetService.createAsset(request);
    }

    @GetMapping("/market")
    public List<MarketAssetResponse> getMarketAssets()
            throws IOException, InterruptedException {

        return assetService.getMarketAssets();
    }

    @GetMapping("/{id}")
    public AssetResponse getAssetById(
            @PathVariable Long id
    ) {

        return assetService.getAssetById(id);

    }

    @DeleteMapping("/{id}")
    public void deleteAsset(
            @PathVariable Long id
    ) {

        assetService.deleteAsset(id);

    }

}