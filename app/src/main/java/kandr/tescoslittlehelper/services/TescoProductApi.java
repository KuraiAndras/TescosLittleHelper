package kandr.tescoslittlehelper.services;

import kandr.tescoslittlehelper.data.ProductData;

public interface TescoProductApi {
    ProductData getProductData(String gtin);
}