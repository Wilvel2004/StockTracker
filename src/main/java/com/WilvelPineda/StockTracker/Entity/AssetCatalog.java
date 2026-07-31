package com.WilvelPineda.StockTracker.Entity;

import com.WilvelPineda.StockTracker.Model.AssetType;
import jakarta.persistence.*;

@Entity
@Table(name = "asset_catalog")
public class AssetCatalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private AssetType type;

    @Column(name = "market_id", nullable = false)
    private String marketId;

    @Column(nullable = false)
    private boolean active = true;

    public AssetCatalog() {
    }

    public AssetCatalog(String symbol,
                        String name,
                        AssetType type,
                        String marketId) {

        this.symbol = symbol;
        this.name = name;
        this.type = type;
        this.marketId = marketId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AssetType getType() {
        return type;
    }

    public void setType(AssetType type) {
        this.type = type;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}