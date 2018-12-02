package kandr.tescoslittlehelper.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "productdata")
public class ProductData {
    @NonNull
    @PrimaryKey()
    @ColumnInfo(name = "gtin")
    public String gtin;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "price")
    public int price;

    @ColumnInfo(name = "inCart")
    public boolean inCart;

    @ColumnInfo(name = "isBought")
    public boolean isBought;

    public ProductData(String name, String description, int price,@NonNull String gtin, boolean inCart, boolean isBought) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.gtin = gtin;
        this.inCart = inCart;
        this.isBought = isBought;
    }
}
