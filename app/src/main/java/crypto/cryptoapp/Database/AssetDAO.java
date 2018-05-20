package crypto.cryptoapp.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;

import java.util.List;

import crypto.cryptoapp.Asset;

/**
 * Created by Mads on 04-05-2018.
 */

@Dao
public interface AssetDAO {

    @Query("SELECT * FROM Asset")
    List<Asset> getUserAssets();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUserAsset(Asset asset);

    @Update
    void updateUserAsset(Asset asset);

    @Delete
    void delete(Asset asset);

}
