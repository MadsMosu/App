package crypto.cryptoapp;


import android.util.Log;
import java.util.List;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.*;

public class APIcalls {

    private String URL_request;


    // api request to get a list of all available assets.
    private void getAssetList(){
        URL_request = "https://min-api.cryptocompare.com/data/all/coinlist";
    }

    private void getCurrentPrice(List<String> assetSymbols){
        StringBuilder sb = new StringBuilder();
        sb.append("https://min-api.cryptocompare.com/data/pricemulti?fsyms=");
        for (String symbol:assetSymbols) {
            sb.append(symbol);
            if(!symbol.equals(assetSymbols.get(assetSymbols.size()-1))){
                sb.append(",");
            }
        }
        sb.append("&tsyms=USD");
    }

    private void getDayPrice(List<String> assetSymbols){
        StringBuilder sb = new StringBuilder();
        sb.append("https://min-api.cryptocompare.com/data/pricemultifull?fsyms=");
        for (String symbol:assetSymbols) {
            sb.append(symbol);
            if(!symbol.equals(assetSymbols.get(assetSymbols.size()-1))){
                sb.append(",");
            }
        }
        sb.append("&tsyms=USD");
    }



    private void loadURLData(String url) {
        JsonObjectRequest DataRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("allCoins", response.toString());


                            /*JSONObject btc_values = response.getJSONObject("BTC".trim());
                            JSONObject eth_values = response.getJSONObject("ETH".trim());

                            Iterator<?> keysBTC = btc_values.keys();
                            Iterator<?> keysETH = eth_values.keys();

                            while(keysBTC.hasNext() && keysETH.hasNext()) {
                                String keyBTC = (String) keysBTC.next();
                                String keyETH = (String) keysETH.next();

                                Asset asset = new Asset(keyBTC, btc_values.getDouble(keyBTC), eth_values.getDouble(keyETH));
                                cardItemsList.add(card);
                            }
                            adapter = new MyAdapter(cardItemsList, getApplicationContext());
                            recyclerView.setAdapter(adapter);*/

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //not implemented
            }
        });
    }

}
