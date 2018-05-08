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
    private List<Asset> assetList;
    private Context context;

    public APIcalls(Context context) {
        this.context = context;
    }

    // api request to get a list of all available assets.
    public List getAssetList(){
        URL_request = "https://min-api.cryptocompare.com/data/all/coinlist";
        return loadURL(URL_request);

    }

    public void getCurrentPrice(List<String> assetSymbols){
        StringBuilder sb = new StringBuilder();
        sb.append("https://min-api.cryptocompare.com/data/pricemulti?fsyms=");
        for (String symbol:assetSymbols) {
            sb.append(symbol);
            if(!symbol.equals(assetSymbols.get(assetSymbols.size()-1))){
                sb.append(",");
            }
        }
        sb.append("&tsyms=USD");
        URL_request = sb.toString();
        loadURL(URL_request);
    }

    public void getDayPrice(List<String> assetSymbols){
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
        loadURL(URL_request);
    }



    private List<Asset> loadURL(String url) {
        assetList = new ArrayList<>();
        JsonObjectRequest DataRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject data = response.getJSONObject("Data");

                            JSONArray array = data.names(); // contains all the keys inside Data



                            for (int i = 0; i < array.length(); i++ ) {
                                String key = array.getString(i);
                                JSONObject obj = data.getJSONObject(key);
                                assetList.add(new Asset(obj.getString("Symbol"), obj.getString
                                        ("CoinName")));
                            }


                        }catch (JSONException e) {
                                e.printStackTrace();}


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "xDD" + error.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(DataRequest);

        return assetList;
    }

}
