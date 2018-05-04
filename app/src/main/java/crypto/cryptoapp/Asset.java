package crypto.cryptoapp;

import android.arch.persistence.room.*;

@Entity
public class Asset {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "asset symbol")
    private String symbol;

    @ColumnInfo(name = "asset name")
    private String assetName;

    @ColumnInfo(name = "asset price")
    private Double price;

    @ColumnInfo(name = "price change")
    private Double change;

    @Ignore
    public Asset() {

    }

    public Asset(int id, String assetName, String symbol) {
        this.id = id;
        this.symbol = symbol;
        this.assetName = assetName;
    }

    @Ignore
    public Asset(int id, String assetName, String symbol, double change, double price) {
        this.id = id;
        this.assetName = assetName;
        this.symbol = symbol;
        this.change = change;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }
}
