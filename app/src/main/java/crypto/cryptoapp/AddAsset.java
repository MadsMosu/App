package crypto.cryptoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import android.widget.Toast;

import com.amitshekhar.DebugDB;


/**
 * Created by Mads on 04-05-2018.
 */

public class AddAsset extends AppCompatActivity {

    DBHandler dbHandler;
    SearchView searchView;
    ListView listView;
    List<Asset> assets;
    ArrayAdapter<Asset> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_asset);
        dbHandler = new DBHandler(this);
        searchView = (SearchView) findViewById(R.id.search_coin);
        listView = (ListView) findViewById(R.id.list_view);


        APIcalls apiCalls = new APIcalls(this);
        assets = apiCalls.getAssetList();

        adapter = new ArrayAdapter<Asset>(this, android.R.layout.simple_list_item_1, assets);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> AV, View v, int pos,
                                    long id) {
                dbHandler.addUserAsset(assets.get((int)AV.getSelectedItemId()));
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

}