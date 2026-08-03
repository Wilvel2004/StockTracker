package com.WilvelPineda.StockTracker.repository;

import com.WilvelPineda.StockTracker.Entity.AssetCatalog;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetCatalogRepository extends JpaRepository<AssetCatalog, Long> {

    List<AssetCatalog>
    findTop10ByActiveTrueAndNameContainingIgnoreCaseOrActiveTrueAndSymbolContainingIgnoreCase(
            String name,
            String symbol
    );

}
