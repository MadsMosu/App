package crypto.cryptoapp;

import android.arch.lifecycle.LiveData;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    List<Asset> assets, updateAssets;
    DBHandler dbHandler;
    ListView listView;
    ArrayAdapter<Asset> adapter;
    APIcalls apiCalls;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiCalls = new APIcalls(this);

        dbHandler = new DBHandler(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        updateAssets = apiCalls.getCurrentPrice(dbHandler.getUserAssetString());
        dbHandler.updateUserAsset(updateAssets);


        listView = (ListView) findViewById(R.id.coinListView);
        assets = dbHandler.getUserAssets();
        Log.d("", assets.toString());
        adapter = new CoinAdapter(this, R.layout.coin_view_layout, assets);
        //listView.setAdapter(adapter);


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddAsset.class));
            }
        });

        /*
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {

                    @Override
                    public void onRefresh(){
                        handler.post(refreshing);
                    }
                });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                boolean enable = false;

                if(listView != null && listView.getChildCount() > 0){
                    boolean firstItemVisible = listView.getFirstVisiblePosition() == 0;
                    boolean topOfFirstItemVisible = listView.getChildAt(0).getTop() == 0;
                    enable = firstItemVisible && topOfFirstItemVisible;
                }
                swipeRefreshLayout.setEnabled(enable);
            }
        });
    }

    private final Runnable refreshing = new Runnable(){
        @Override
        public void run() {
            try {
                if(swipeRefreshLayout.isRefreshing()){
                    //fetch new data here
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                    //update list here
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        */
    }



}
