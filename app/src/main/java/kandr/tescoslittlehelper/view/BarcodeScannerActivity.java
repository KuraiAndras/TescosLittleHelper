package kandr.tescoslittlehelper.view;

import android.content.Context;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
                DbManager.insert(getApplicationContext(), DbManager.getMockedProductData());
                vibrateAndFinish();
            }
        });
    }

    private void initUiElements() {
        btnAddMockedProduct = findViewById(R.id.btnAddMockedProduct);
    }

    private void vibrateAndFinish(){
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        }
        finish();
    }

    @Override
    public void onScanned(Barcode barcode) {
        ProductData productData = NetworkManager.getInstance().getProduct(barcode.displayValue);
        DbManager.insert(getApplicationContext(), productData);
        vibrateAndFinish();
    }

    @Override
    public void onScannedMultiple(List<Barcode> list) {
    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {
    }

    @Override
    public void onScanError(String s) {
    }

    @Override
    public void onCameraPermissionDenied() {
        Toast.makeText(getApplicationContext(), "No permission to Camera", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public ProductData getProductData() {
        return null;
    }
}
