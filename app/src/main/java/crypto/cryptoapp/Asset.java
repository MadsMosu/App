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

    public Asset() {

    }

    public Asset(int id, String assetName, String symbol, double change, double price) {
        this.id = id;
        this.assetName = assetName;
        this.symbol = symbol;
        this.change = change;
        this.price = price;
    }

    public Asset(String assetName, String symbol, double change, double price) {
        this.assetName = assetName;
        this.symbol = symbol;
        this.change = change;
        this.price = price;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

    public void setassetName(String assetName) {
        this.assetName = assetName;
    }

    public String getassetName() {
        return this.assetName;
    }

    public void setsymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getsymbol() {
        return this.symbol;
    }

    public Double getchange() {
        return this.change;
    }

    public void setchange(Double change) {
        this.change = change;
    }

    public Double getprice() {
        return price;
    }

    public void setprice(Double price) {
        this.price = price;
    }
}
