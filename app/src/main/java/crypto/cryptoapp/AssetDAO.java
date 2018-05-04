package crypto.cryptoapp;

import android.arch.persistence.room.*;

import java.util.List;

/**
 * Created by Mads on 04-05-2018.
 */

@Dao
public interface AssetDAO {

    @Query("SELECT * FROM asset")
    List<Asset> getUserAssets();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserAsset(Asset asset);

    @Delete
    void delete(Asset asset);

}
