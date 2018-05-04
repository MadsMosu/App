package crypto.cryptoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import android.widget.Toast;


/**
 * Created by Mads on 04-05-2018.
 */

public class AddAsset extends AppCompatActivity {

    SearchView searchView;
    ListView listView;
    List<Asset> assets;
    ArrayAdapter<Asset> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_asset);

        searchView = (SearchView) findViewById(R.id.search_coin);
        listView = (ListView) findViewById(R.id.list_view);


        APIcalls apiCalls = new APIcalls(this);
        assets = apiCalls.getAssetList();

        adapter = new ArrayAdapter<Asset>(this, android.R.layout.simple_list_item_1, assets);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                for (Asset asset: assets) {
                    if(asset.getAssetName().contains(query)){
                        adapter.getFilter().filter(query);
                        break;
                    }
                    else {
                        Toast.makeText(AddAsset.this, "No coins found", Toast.LENGTH_LONG).show();
                    }
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}