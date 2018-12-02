package kandr.tescoslittlehelper.view.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import kandr.tescoslittlehelper.R;
import kandr.tescoslittlehelper.data.ProductData;
import kandr.tescoslittlehelper.service.Managers.DbManager;
import kandr.tescoslittlehelper.view.Updatable;
import kandr.tescoslittlehelper.view.adapters.ScannedProductsAdapter;

public class EditProductActivity extends AppCompatActivity implements Updatable {

    private EditText editPrice;
    private EditText editDescription;
    private EditText editName;
    private Button saveEdit;

    private ProductData productData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        Bundle bundle = getIntent().getExtras();

        String gtin = "";

        if (bundle != null) {
            gtin = bundle.getString(ScannedProductsAdapter.GTIN_MESSAGE);
        }

        DbManager.loadItemInTheBackground(getApplicationContext(), this, gtin);

        initUiElements();

        saveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productData.name = editName.getText().toString();
                productData.description = editDescription.getText().toString();
                productData.price = Integer.parseInt(editPrice.getText().toString());
                DbManager.addOrUpdate(getApplicationContext(), productData);
                finish();
            }
        });
    }

    private void initUiElements() {
        editPrice = findViewById(R.id.editEditPrice);
        editDescription = findViewById(R.id.editEditDescription);
        editName = findViewById(R.id.edtEditName);
        saveEdit = findViewById(R.id.editSaveEdit);
    }

    @Override
    public void updateAll(List<ProductData> productDataList) {

    }

    @Override
    public void update(ProductData productData) {
        editName.setText(productData.name);
        editDescription.setText(productData.description);
        editPrice.setText(String.valueOf(productData.price));

        this.productData = productData;
    }

    @Override
    public void removeAll(Context applicationContext) {

    }
}
