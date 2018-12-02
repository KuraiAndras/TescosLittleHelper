package kandr.tescoslittlehelper.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;
import kandr.tescoslittlehelper.R;
import kandr.tescoslittlehelper.data.ProductData;
import kandr.tescoslittlehelper.services.DbManager;
import kandr.tescoslittlehelper.services.NetworkManager;
import kandr.tescoslittlehelper.services.ProductDataHolder;

public class BarcodeScannerActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener, ProductDataHolder {
    private Button btnAddMockedProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);

        initUiElements();

        btnAddMockedProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbManager.insertIntoDatabase(getApplicationContext(), DbManager.getMockedProductData());
            }
        });
    }

    private void initUiElements() {
        btnAddMockedProduct = findViewById(R.id.btnAddMockedProduct);
    }

    @Override
    public void onScanned(Barcode barcode) {
        ProductData productData = NetworkManager.getInstance().getProduct(barcode.displayValue);
        DbManager.insertIntoDatabase(getApplicationContext(), productData);
    }

    @Override
    public void onScannedMultiple(List<Barcode> list) {
        // multiple barcodes scanned
    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {
        // barcode scanned from bitmap image
    }

    @Override
    public void onScanError(String s) {
        // scan error
    }

    @Override
    public void onCameraPermissionDenied() {
        // camera permission denied
    }

    @Override
    public ProductData getProductData() {
        return null;
    }
}
