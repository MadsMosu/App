package crypto.cryptoapp;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class DBHandler {
    private AssetDAO assetDao;
    private List<Asset> userAssets;

    DBHandler(Context context) {
        AppDatabase db = AppDatabase.getInMemoryDatabase(context);
        assetDao = db.assetDao();
        userAssets = assetDao.getUserAssets();
    }

    List<Asset> getUserAssets() {
        return userAssets;
    }

    public void addUserAsset (Asset asset) {
        new insertAsyncTask(assetDao).execute(asset);
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
}


