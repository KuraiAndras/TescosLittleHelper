package kandr.tescoslittlehelper.data;

import kandr.tescoslittlehelper.data.ProductData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface TescoProductApi {
    @GET("/product")
    Call<ProductData> getProductData(
            @Query("gtin") String gtin,
            @Header("Ocp-Apim-Subscription-Key") String subscriptionKey
    );
}
