package kandr.tescoslittlehelper.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import kandr.tescoslittlehelper.R;
import kandr.tescoslittlehelper.data.ProductData;
import kandr.tescoslittlehelper.services.DbManager;
import kandr.tescoslittlehelper.view.adapters.CartAdapter;
import kandr.tescoslittlehelper.view.adapters.ProductDataClickListener;

public class CartActivity extends AppCompatActivity implements ProductDataClickListener {

    private RecyclerView recyclerView;
    private CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.CartRecyclerView);
        adapter = new CartAdapter(this);
        DbManager.loadCartItemsInTheBackground(getApplicationContext(), adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemChanged(final ProductData item) {
        DbManager.addOrUpdate(getApplicationContext(), item);
    }
}
