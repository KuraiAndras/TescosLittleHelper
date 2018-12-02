package kandr.tescoslittlehelper.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import kandr.tescoslittlehelper.R;
import kandr.tescoslittlehelper.data.ProductData;
import kandr.tescoslittlehelper.service.Managers.DbManager;
import kandr.tescoslittlehelper.view.Updatable;
import kandr.tescoslittlehelper.view.adapters.CartAdapter;
import kandr.tescoslittlehelper.view.adapters.ProductDataClickListener;

public class CartActivity extends AppCompatActivity implements ProductDataClickListener {

    private Button clearAll;

    private RecyclerView recyclerView;
    private CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initRecyclerView();

        initUiElements();

        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Updatable updatable = adapter;
                updatable.removeAll(getApplicationContext());
            }
        });
    }

    private void initUiElements() {
        clearAll = findViewById(R.id.clearAll);
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.CartRecyclerView);
        adapter = new CartAdapter();
        DbManager.loadCartItemsInTheBackground(getApplicationContext(), adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemChanged(final ProductData item) {
        DbManager.addOrUpdate(getApplicationContext(), item);
    }
}
