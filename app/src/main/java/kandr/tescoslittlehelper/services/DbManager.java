package kandr.tescoslittlehelper.services;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;
import java.util.UUID;

import kandr.tescoslittlehelper.data.ProductData;
import kandr.tescoslittlehelper.data.ProductDataDao;
import kandr.tescoslittlehelper.data.ProductDataDatabase;
import kandr.tescoslittlehelper.view.adapters.ScannedProductsAdapter;

public class DbManager {
    private static ProductDataDatabase productDataDatabase;

    private static ProductDataDatabase getProductDataDatabaseInstance(Context context) {
        if (productDataDatabase == null) {
            productDataDatabase = buildDatabase(context);
        }

        return productDataDatabase;
    }

    //TODO: proper migration
    private static ProductDataDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(
                context,
                ProductDataDatabase.class,
                DbNames.ProductDatabase
        ).fallbackToDestructiveMigration()
                .build();
    }

    private DbManager() {
    }

    public static ProductData getMockedProductData() {
        String randomGtin = UUID.randomUUID().toString();
        randomGtin = randomGtin.substring(0, 12);
        return new ProductData(
                "Mocked Item",
                "Lorem ipsum dolor et ami",
                1000,
                randomGtin,
                false);
    }

    public static ProductData getMockedProductData(String gtin) {
        return new ProductData(
                "Mocked Item",
                "Lorem ipsum dolor et ami",
                1000,
                gtin,
                false);
    }

    public static class DbNames {
        static final String ProductDatabase = "productdata";
    }


    public static void getAll(final Context applicationContext) {
        new AsyncTask<Void, Void, List<ProductData>>() {
            @Override
            protected List<ProductData> doInBackground(Void... voids) {
                return getProductDataDatabaseInstance(applicationContext).productDataDao().getAll();
            }
        }.execute();
    }

    public static void get(final Context applicationContext, final String gtin) {
        new AsyncTask<Void, Void, ProductData>() {
            @Override
            protected ProductData doInBackground(Void... voids) {
                return getProductDataDatabaseInstance(applicationContext).productDataDao().get(gtin);
            }
        }.execute();
    }

    public static void addOrUpdate(final Context applicationContext, final ProductData productData) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                ProductDataDao dao = getProductDataDatabaseInstance(applicationContext).productDataDao();
                ProductData data = dao.get(productData.gtin);
                if (data == null) {
                    dao.insert(productData);
                }
                return true;
            }
        }.execute();
    }

    public static void delete(final Context applicationContext, final ProductData item) {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                getProductDataDatabaseInstance(applicationContext).productDataDao().deleteItem(item);
                return true;
            }
        }.execute();
    }

    public static void loadItemsInTheBackground(final Context applicationContext, final ScannedProductsAdapter scannedProductsAdapter) {
        new AsyncTask<Void, Void, List<ProductData>>() {

            @Override
            protected List<ProductData> doInBackground(Void... voids) {
                return getProductDataDatabaseInstance(applicationContext).productDataDao().getAll();
            }

            @Override
            protected void onPostExecute(List<ProductData> productList) {
                scannedProductsAdapter.update(productList);
            }
        }.execute();
    }
}