package kandr.tescoslittlehelper.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import kandr.tescoslittlehelper.R;

public class MainActivity extends AppCompatActivity {
    private Button btnScanItem;
    private Button btnScannedProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUiElements();

        btnScanItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BarcodeScannerActivity.class);
                startActivity(intent);
            }
        });
        btnScannedProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ScannedProductsActivity.class);
                startActivity(intent);
            }
        });


        requestPermissions();
    }

    private void initUiElements(){
        btnScanItem = findViewById(R.id.btnScanItem);
        btnScannedProducts = findViewById(R.id.btnScannedProducts);
    }

    private void requestPermissions(){
        String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.VIBRATE, Manifest.permission.INTERNET};
        ActivityCompat.requestPermissions(this, permissions, PackageManager.PERMISSION_GRANTED);
    }
}
