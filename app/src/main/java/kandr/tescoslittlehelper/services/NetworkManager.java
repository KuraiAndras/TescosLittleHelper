package kandr.tescoslittlehelper.services;

import kandr.tescoslittlehelper.data.ProductData;

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
