package crypto.cryptoapp;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.*;

public class APIcalls {

    private String URL_request;
    private List<Asset> assetList, fullAssetList;
    private Context context;


    public APIcalls(Context context) {
        this.context = context;
    }

    // api request to get a list of all available assets.
    public List<Asset> getAssetList(){
        URL_request = "https://min-api.cryptocompare.com/data/all/coinlist";
        return loadURL(URL_request);

    }

    public List<Asset> getCurrentPrice(List<String> assetSymbols){
        StringBuilder sb = new StringBuilder();
        sb.append("https://min-api.cryptocompare.com/data/pricemultifull?fsyms=");
        for (String symbol:assetSymbols) {
            sb.append(symbol);
            if(!symbol.equals(assetSymbols.get(assetSymbols.size()-1))){
                sb.append(",");
            }
        }
        sb.append("&tsyms=USD");
        URL_request = sb.toString();
        return loadPrices(URL_request);
    }



    private List<Asset> loadURL(String url) {
        fullAssetList = new ArrayList<>();
        final JsonObjectRequest DataRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject data;
                        JSONArray array;
                        try {
                                    data = response.getJSONObject("Data");
                                    array = data.names(); // contains all the keys inside Data

                                    for (int i = 0; i < array.length(); i++) {
                                        String key = array.getString(i);
                                        JSONObject obj = data.getJSONObject(key);
                                        fullAssetList.add(new Asset(obj.getString("Symbol"), obj
                                                .getString("CoinName")));
                                    }
                           }
                        catch (JSONException e) {

                            Log.d("shit", "I got an error", e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "cd" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(DataRequest);

        return fullAssetList;
    }


    private List<Asset> loadPrices(String url) {
        assetList = new ArrayList<>();
        final JsonObjectRequest DataRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject data;
                        JSONArray array;
                        try {
                            data = response.getJSONObject("RAW");


                            array = data.names(); // contains all the keys inside RAW

                            for(int i = 0; i<array.length();i++){
                                String key = array.getString(i);
                                JSONObject obj = data.getJSONObject(key);
                                JSONArray theArray = obj.names();

                                for (int j = 0; j < theArray.length(); j++) {

                                    String key1 = theArray.getString(j);
                                    JSONObject obj1 = obj.getJSONObject(key1);
                                    assetList.add(new Asset(obj1.getString("FROMSYMBOL"), obj1
                                            .getString("PRICE"), obj1.getString
                                            ("CHANGEPCT24HOUR")));

                                }}

                        }
                        catch (JSONException e) {

                            Log.d("shit", "I got an error", e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "cd" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(DataRequest);

        return assetList;
    }

}
