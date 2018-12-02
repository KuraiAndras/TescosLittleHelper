package kandr.tescoslittlehelper.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kandr.tescoslittlehelper.R;
import kandr.tescoslittlehelper.data.ProductData;
import kandr.tescoslittlehelper.service.Managers.DbManager;

public class SaveProductActivity extends AppCompatActivity {

    private EditText editPrice;
    private EditText editDescription;
    private EditText editName;
    private Button saveEdit;
    private Button dontSave;
    private String gtin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_product);

        initUiElements();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            gtin = bundle.getString(BarcodeScannerActivity.GTIN_MESSAGE);
        }

        saveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductData productData = new ProductData(
                        editName.getText().toString(),
                        editDescription.getText().toString(),
                        Integer.parseInt(editPrice.getText().toString()),
                        gtin != null ? gtin : "",
                        false,
                        false
                );
                DbManager.addOrUpdate(getApplicationContext(), productData);

                finish();
            }
        });

        dontSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initUiElements() {
        editPrice = findViewById(R.id.saveEditPrice);
        editDescription = findViewById(R.id.saveEditDescription);
        editName = findViewById(R.id.saveEditName);
        saveEdit = findViewById(R.id.saveSaveEdit);
        dontSave = findViewById(R.id.saveDontSave);
    }
}
