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
        new insertAsyncTask(assetDao).execute(asset);
    }

    public void updateUserAsset(Asset asset) {
        // assetDao.updateUserAsset(assets.toArray(new Asset[assets.size()]));
        new updateAsyncTask(assetDao).execute(asset);
    }


    private static class insertAsyncTask extends AsyncTask<Asset, Void, Void> {

        private AssetDAO asyncTaskDao;

        insertAsyncTask(AssetDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Asset... params) {
            asyncTaskDao.addUserAsset(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Asset, Void, Void> {

        private AssetDAO asyncTaskDao;

        updateAsyncTask(AssetDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Asset... params) {
            asyncTaskDao.updateUserAsset(params[0]);
            return null;
        }
    }
}


