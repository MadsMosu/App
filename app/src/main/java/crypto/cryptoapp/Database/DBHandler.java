package crypto.cryptoapp.Database;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import crypto.cryptoapp.Asset;

public class DBHandler {
    private AssetDAO assetDao;
    private static DBHandler dbHandler;

    private DBHandler(Context context) {
        AppDatabase db = AppDatabase.getInMemoryDatabase(context);
        assetDao = db.assetDao();
        assetDao.addUserAsset(new Asset("BTC", "Bitcoin"));
        assetDao.addUserAsset(new Asset("ETH", "Ethereum"));
        assetDao.addUserAsset(new Asset("NEO", "Neo"));
    }

    public static synchronized DBHandler getInstance(Context context) {
        if (dbHandler == null) {
            dbHandler = new DBHandler(context.getApplicationContext());
        }
        return dbHandler;
    }

    public List<Asset> getUserAssets() {
        return assetDao.getUserAssets();
    }

    public List<String> getUserAssetString() {
        List<String> stringList = new ArrayList<>();
        for (Asset asset : assetDao.getUserAssets()) {
            stringList.add(asset.getSymbol());
        }
        return stringList;
    }

    public void addUserAsset(Asset asset) {
        assetDao.addUserAsset(asset);
    }

    public void updateUserAsset(List<Asset> assets) {
        assetDao.updateUserAsset(assets.toArray(new Asset[assets.size()]));

    }

}


