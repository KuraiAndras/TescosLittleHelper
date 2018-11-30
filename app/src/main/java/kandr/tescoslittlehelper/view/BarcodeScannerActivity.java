package kandr.tescoslittlehelper.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import kandr.tescoslittlehelper.R;

public class BarcodeScannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);

        initUiElements();
    }

    private void initUiElements(){

    }
}
