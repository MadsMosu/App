package crypto.cryptoapp;

import android.arch.persistence.room.*;

/**
 * Created by Mads on 04-05-2018.
 */

@Dao
public interface AssetDAO {

    @Query("SELECT * FROM asset")
    List<Asset> getAll();

    @Insert
    void insertAll(Asset... assets);

    @Delete
    void dele(Asset asset);
}
