package crypto.cryptoapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Mads on 08-05-2018.
 */

public class CoinAdapter extends ArrayAdapter<Asset>{
    private Context context;
    private int resource;


    public CoinAdapter(@NonNull Context context, int resource, List<Asset> assets) {
        super(context, resource, assets);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getAssetName();
        String symbol = getItem(position).getSymbol();

        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView = inflater.inflate(this.resource, parent, false);

        TextView twSymbol = (TextView) convertView.findViewById(R.id.symbol);

        twSymbol.setText(name + " ("+symbol+")");


        return convertView;
    }
}
