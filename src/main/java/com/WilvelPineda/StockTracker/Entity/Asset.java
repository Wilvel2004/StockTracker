package com.WilvelPineda.StockTracker.Entity;

import com.WilvelPineda.StockTracker.Model.AssetType;
import jakarta.persistence.*;

@Entity
@Table(name = "assets")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;

    private String name;

    @Enumerated(EnumType.STRING)
    private AssetType type;

    private String marketId;

    public Asset(
            String symbol,
            String name,
            AssetType type,
            String marketId
    ) {
        this.symbol = symbol;
        this.name = name;
        this.type = type;
        this.marketId = marketId;
    }

    public Asset() {
    }


    public Long getId() {
        return id;
    }


    public String getSymbol() {
        return symbol;
    }


    public String getName() {
        return name;
    }


    public AssetType getType() {
        return type;
    }


    public String getMarketId() {return marketId;}

}
