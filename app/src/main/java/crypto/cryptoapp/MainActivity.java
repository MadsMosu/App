package crypto.cryptoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;

import crypto.cryptoapp.Database.DBHandler;
import crypto.cryptoapp.Service.APIcalls;
import crypto.cryptoapp.Service.OnFinishListener;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    List<Asset> theAssets;
    DBHandler dbHandler;
    ListView listView;
    ArrayAdapter<Asset> adapter;
    APIcalls apiCalls;
    SwipeRefreshLayout swipeRefreshLayout;
    Handler handler = new Handler();
    //Listener for new content
    OnFinishListener ofl = new OnFinishListener() {
        @Override
        public void onFinish(List<Asset> assets) {
            theAssets = dbHandler.getUserAssets();
            adapter.clear();
            adapter.addAll(theAssets);
            adapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHandler = DBHandler.getInstance(this);
        apiCalls = new APIcalls(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        apiCalls.getCurrentPrice(dbHandler.getUserAssetString(), ofl);
        theAssets = dbHandler.getUserAssets();
        listView = findViewById(R.id.coinListView);
        adapter = new CoinAdapter(this, R.layout.coin_view_layout, theAssets);
        listView.setAdapter(adapter);


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddAsset.class));
            }
        });

        swipeRefreshLayout = findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {

                    @Override
                    public void onRefresh() {
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

                if (listView != null && listView.getChildCount() > 0) {
                    boolean firstItemVisible = listView.getFirstVisiblePosition() == 0;
                    boolean topOfFirstItemVisible = listView.getChildAt(0).getTop() == 0;
                    enable = firstItemVisible && topOfFirstItemVisible;
                }
                swipeRefreshLayout.setEnabled(enable);
            }
        });
    }

    private final Runnable refreshing = new Runnable() {
        @Override
        public void run() {
            try {
                if (swipeRefreshLayout.isRefreshing()) {
                    apiCalls.getCurrentPrice(dbHandler.getUserAssetString(), ofl);

                } else {

                    swipeRefreshLayout.setRefreshing(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    };

    @Override
    protected void onStart() {
        super.onStart();
        apiCalls.getCurrentPrice(dbHandler.getUserAssetString(), ofl);
    }


}
