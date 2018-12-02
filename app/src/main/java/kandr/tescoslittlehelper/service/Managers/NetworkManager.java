package kandr.tescoslittlehelper.service.Managers;

import kandr.tescoslittlehelper.data.ProductData;
import kandr.tescoslittlehelper.data.TescoProductApi;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO: This should be a service
public class NetworkManager {
    private static final String SERVICE_URL = "https://dev.tescolabs.com";
    //TODO: Email Tesco again for subscription key
    private static final String SERVICE_KEY = "{subscription key}";

    private static NetworkManager instance;

    public static NetworkManager getInstance() {
        if (instance == null) {
            instance = new NetworkManager();
        }
        return instance;
    }

    private TescoProductApi tescoProductApi;

    private NetworkManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVICE_URL)
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        tescoProductApi = retrofit.create(TescoProductApi.class);
    }

    public Call<ProductData> getProduct(String gtin) {
        return tescoProductApi.getProductData(gtin, SERVICE_KEY);
    }
}
