package crypto.cryptoapp.Service;

import java.util.List;
import crypto.cryptoapp.Asset;

public interface OnFinishListener {
    void onFinish(List<Asset> assets);
}
