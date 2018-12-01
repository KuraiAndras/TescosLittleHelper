package kandr.tescoslittlehelper.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "productdata")
public class ProductData {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "price")
    public int price;

    @ColumnInfo(name = "gtin")
    public String gtin;

    public ProductData(String name, String description, int price, String gtin) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.gtin = gtin;
    }


}
