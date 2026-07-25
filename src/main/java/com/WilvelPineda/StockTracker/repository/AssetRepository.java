package com.WilvelPineda.StockTracker.repository;

import com.WilvelPineda.StockTracker.Entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

}
