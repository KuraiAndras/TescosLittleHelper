package kandr.tescoslittlehelper.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;
import kandr.tescoslittlehelper.R;
import kandr.tescoslittlehelper.data.ProductData;
import kandr.tescoslittlehelper.service.Managers.DbManager;
import kandr.tescoslittlehelper.service.Managers.NetworkManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarcodeScannerActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {
    private Button btnAddMockedProduct;
    private static final String TAG = "BarcodeScannerActivity";
    public static final String GTIN_MESSAGE = "kandr.tescoslittlehelper.view.activities.BarcodeScannerActivity.GTIN_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);

        initUiElements();

        btnAddMockedProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbManager.addOrUpdate(getApplicationContext(), DbManager.getMockedProductData());
                vibrateAndFinish();
            }
        });
    }

    private void initUiElements() {
        btnAddMockedProduct = findViewById(R.id.btnAddMockedProduct);
    }

    private void vibrateAndFinish() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        }
        finish();
    }

    @Override
    public void onScanned(final Barcode barcode) {
        //TODO: this should be in a service
        NetworkManager.getInstance().getProduct(barcode.displayValue).enqueue(new Callback<ProductData>() {
            @Override
            public void onResponse(@NonNull Call<ProductData> call,
                                   @NonNull Response<ProductData> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    DbManager.addOrUpdate(getApplicationContext(), response.body());
                } else {
                    Context context = getApplicationContext();
                    Intent intent = new Intent(context, SaveProductActivity.class);
                    intent.putExtra(GTIN_MESSAGE, barcode.displayValue);
                    context.startActivity(intent);

                    Toast.makeText(BarcodeScannerActivity.this,
                            "Error: " + response.message(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProductData> call, @NonNull Throwable throwable) {
                throwable.printStackTrace();
                Toast.makeText(BarcodeScannerActivity.this,
                        "Network request error occurred, check LOG",
                        Toast.LENGTH_SHORT).show();
            }
        });

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
}
