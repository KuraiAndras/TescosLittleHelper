package kandr.tescoslittlehelper.view;

import java.util.List;

import kandr.tescoslittlehelper.data.ProductData;

public interface Updatable {
    void updateAll(List<ProductData> productDataList);

    void update(ProductData productData);
}
