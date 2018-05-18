package crypto.cryptoapp;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class DBHandler {
    private AssetDAO assetDao;


    DBHandler(Context context) {
        AppDatabase db = AppDatabase.getInMemoryDatabase(context);
        assetDao = db.assetDao();
    }

    List<Asset> getUserAssets() {
        return assetDao.getUserAssets();
    }

    public void addUserAsset (Asset asset) {
        assetDao.addUserAsset(asset);
    }

    public void updateUserAsset (List<Asset> assets) {
        assetDao.updateUserAsset(assets.toArray(new Asset[assets.size()]));
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


