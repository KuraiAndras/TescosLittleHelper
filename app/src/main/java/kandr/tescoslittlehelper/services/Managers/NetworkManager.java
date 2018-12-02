package kandr.tescoslittlehelper.services.Managers;

import kandr.tescoslittlehelper.data.ProductData;
import kandr.tescoslittlehelper.services.TescoHttpClient;
import kandr.tescoslittlehelper.services.TescoProductApi;

public class NetworkManager {
    private static NetworkManager instance;

    public static NetworkManager getInstance() {
        if (instance == null)
            instance = new NetworkManager();

        return instance;
    }

    private TescoProductApi tescoProductApi = new TescoHttpClient();

    private NetworkManager() {
    }

    public ProductData getProduct(String gtin) {
        return tescoProductApi.getProductData(gtin);
    }
}
