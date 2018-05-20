package crypto.cryptoapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Mads on 08-05-2018.
 */

public class CoinAdapter extends ArrayAdapter<Asset> {
    private Context context;
    private int resource;
    private List<Asset> assets;


    public CoinAdapter(@NonNull Context context, int resource, List<Asset> assets) {
        super(context, resource, assets);
        this.assets = assets;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DecimalFormat df = new DecimalFormat("#.##");
        String symbol, sign;
        double price, change;
        symbol = getItem(position).getSymbol();
        if (getItem(position).getPrice() != null & getItem(position).getChange() != null) {

            price = (getItem(position).getPrice());
            change = (getItem(position).getChange());
        } else {
            price = 0;
            change = 0;
        }

        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView = inflater.inflate(this.resource, parent, false);

        TextView twSymbol = (TextView) convertView.findViewById(R.id.symbol);
        TextView twPrice = (TextView) convertView.findViewById(R.id.price);
        TextView twChange = (TextView) convertView.findViewById(R.id.change);


        twSymbol.setText(symbol);

        if (!isNegative(change)) {
            sign = "+";
        } else {
            sign = "";
        }

        twPrice.setText("$" + df.format(price));
        twChange.setText(sign + df.format(change) + "%");


        return convertView;
    }

    public static boolean isNegative(double d) {
        return Double.compare(d, 0.0) < 0;
    }
}
