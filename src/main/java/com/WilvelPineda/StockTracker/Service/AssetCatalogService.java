package com.WilvelPineda.StockTracker.Service;

import com.WilvelPineda.StockTracker.DTO.Response.AssetCatalogResponse;
import com.WilvelPineda.StockTracker.repository.AssetCatalogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetCatalogService {


    private final AssetCatalogRepository repository;


    public AssetCatalogService(
            AssetCatalogRepository repository
    ){
        this.repository = repository;
    }


    public List<AssetCatalogResponse> search(String query){

        return repository
                .findTop10ByActiveTrueAndNameContainingIgnoreCaseOrActiveTrueAndSymbolContainingIgnoreCase(
                        query,
                        query
                )
                .stream()
                        .map(asset ->
                                new AssetCatalogResponse(
                                        asset.getId(),
                                        asset.getSymbol(),
                                        asset.getName(),
                                        asset.getType()
                                )
                        )
                        .toList();

    }

}