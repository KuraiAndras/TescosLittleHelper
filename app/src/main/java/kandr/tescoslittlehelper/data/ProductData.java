package kandr.tescoslittlehelper.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "productdata")
public class ProductData {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public Long id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "price")
    public int price;

    @ColumnInfo(name = "gtin")
    public String gtin;

    @ColumnInfo(name = "inCart")
    public boolean inCart;

    public ProductData(String name, String description, int price, String gtin, boolean inCart) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.gtin = gtin;
        this.inCart = inCart;
    }


}
