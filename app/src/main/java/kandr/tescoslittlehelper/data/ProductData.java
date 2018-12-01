package kandr.tescoslittlehelper.data;

import java.util.ArrayList;

public class ProductData {
    public String name;
    public String about;
    public int price;
    public String gtin;

    public ProductData(String name, String about, int price, String gtin) {
        this.name = name;
        this.about = about;
        this.price = price;
        this.gtin = gtin;
    }
}
