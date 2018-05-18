package crypto.cryptoapp;

import android.arch.lifecycle.LiveData;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    List<Asset> assets;
    DBHandler dbHandler;
    ListView listView;
    ArrayAdapter<Asset> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new DBHandler(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //listView = (ListView) findViewById(R.id.users_asset_list);


        assets = dbHandler.getUserAssets();
        adapter = new ArrayAdapter<Asset>(this, android.R.layout.simple_list_item_1, assets);
        //listView.setAdapter(adapter);


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddAsset.class));
            }
        });
    }



}
