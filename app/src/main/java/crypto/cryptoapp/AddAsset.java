package crypto.cryptoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import crypto.cryptoapp.Database.DBHandler;
import crypto.cryptoapp.Service.APIcalls;
import crypto.cryptoapp.Service.OnFinishListener;


/**
 * Created by Mads on 04-05-2018.
 */

public class AddAsset extends AppCompatActivity {

    DBHandler dbHandler;
    SearchView searchView;
    ListView listView;
    List<Asset> allAssets;
    ArrayAdapter<Asset> adapter;
    APIcalls apiCalls;


    OnFinishListener ofl = new OnFinishListener() {
        @Override
        public void onFinish(List<Asset> assets) {
            allAssets = assets;
            adapter.clear();
            adapter.addAll(allAssets);
            adapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_asset);
        dbHandler = DBHandler.getInstance(this);
        searchView = findViewById(R.id.search_coin);
        listView = findViewById(R.id.list_view);


        apiCalls = new APIcalls(this);
        allAssets = apiCalls.getAssetList(ofl);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allAssets);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> AV, View v, int pos,
                                    long id) {
                dbHandler.addUserAsset(allAssets.get(pos));
                startActivity(new Intent(AddAsset.this, MainActivity.class));


            }

        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    protected void onStart() {
        super.onStart();
        allAssets = apiCalls.getAssetList(ofl);
    }


}