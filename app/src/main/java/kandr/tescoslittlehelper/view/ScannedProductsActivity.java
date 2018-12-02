package kandr.tescoslittlehelper.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import kandr.tescoslittlehelper.R;
import kandr.tescoslittlehelper.data.ProductData;
import kandr.tescoslittlehelper.services.DbManager;
import kandr.tescoslittlehelper.view.adapters.ScannedProductsAdapter;

public class ScannedProductsActivity extends AppCompatActivity implements ScannedProductsAdapter.ProductDataClickListener {
    private RecyclerView recyclerView;
    private ScannedProductsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanned_products);

        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.ScannedProductsRecyclerView);
        adapter = new ScannedProductsAdapter(this);
        DbManager.loadItemsInTheBackground(getApplicationContext(), adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemChanged(final ProductData item) {
        DbManager.addOrUpdate(getApplicationContext(), item);
    }
}
