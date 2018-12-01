package kandr.tescoslittlehelper.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;
import kandr.tescoslittlehelper.R;

public class BarcodeScannerActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);

        initUiElements();
    }

    private void initUiElements() {

    }

    @Override
    public void onScanned(Barcode barcode) {
        Toast.makeText(getApplicationContext(), barcode.displayValue, Toast.LENGTH_LONG).show();
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
}
