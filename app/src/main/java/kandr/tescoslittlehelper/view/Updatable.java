package kandr.tescoslittlehelper.view;

import android.content.Context;

import java.util.List;

import kandr.tescoslittlehelper.data.ProductData;

public interface Updatable {
    void updateAll(List<ProductData> productDataList);

    void update(ProductData productData);

    void removeAll(Context applicationContext);
}
