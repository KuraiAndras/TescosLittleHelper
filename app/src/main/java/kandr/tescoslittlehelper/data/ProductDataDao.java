package kandr.tescoslittlehelper.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ProductDataDao {
    @Query("Select * FROM productdata")
    List<ProductData> getAll();

    @Query("Select * FROM productdata WHERE gtin == :gtinQuery")
    ProductData get(String gtinQuery);

    @Insert
    long insert(ProductData productData);

    @Update
    void update(ProductData productData);

    @Delete
    void deleteItem(ProductData productData);
}
