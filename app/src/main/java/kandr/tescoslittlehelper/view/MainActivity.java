package kandr.tescoslittlehelper.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import kandr.tescoslittlehelper.R;

public class MainActivity extends AppCompatActivity {
    private Button btnScanItem;

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
    }

    private void initUiElements(){
        btnScanItem = findViewById(R.id.btnScanItem);
    }
}
