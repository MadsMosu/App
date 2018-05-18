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
        String symbol = getItem(position).getSymbol();
        String price = getItem(position).getPrice().toString();
        String change = getItem(position).getChange().toString();

        Asset asset = new Asset(symbol, price, change);

        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView = inflater.inflate(this.resource, parent, false);

        TextView twSymbol = (TextView) convertView.findViewById(R.id.symbol);
        TextView twPrice = (TextView) convertView.findViewById(R.id.price);
        TextView twChange = (TextView) convertView.findViewById(R.id.change);

        twSymbol.setText(symbol);
        twPrice.setText(price);
        twChange.setText(change);

        return convertView;
    }
}
