package com.WilvelPineda.StockTracker.Controller;

import com.WilvelPineda.StockTracker.DTO.Request.CreateAssetRequest;
import com.WilvelPineda.StockTracker.DTO.Response.AssetCatalogResponse;
import com.WilvelPineda.StockTracker.DTO.Response.AssetResponse;
import com.WilvelPineda.StockTracker.Service.AssetCatalogService;
import com.WilvelPineda.StockTracker.Service.AssetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
@CrossOrigin
public class AssetCatalogController {


    private final AssetCatalogService service;

    private final AssetService assetService;


    public AssetCatalogController(
            AssetCatalogService service,
            AssetService assetService
    ){
        this.service = service;
        this.assetService = assetService;
    }



    @GetMapping("/search")
    public List<AssetCatalogResponse> search(
            @RequestParam String query
    ){

        return service.search(query);

    }

    @PostMapping
    public AssetResponse create(
            @RequestBody CreateAssetRequest request
    ){
        return assetService.createAsset(request);
    }

}
