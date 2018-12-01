package kandr.tescoslittlehelper.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kandr.tescoslittlehelper.R;
import kandr.tescoslittlehelper.data.ProductData;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private final List<ProductData> items;

    private ProductDataClickListener listener;

    public ProductAdapter(ProductDataClickListener listener) {
        this.listener = listener;
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_shopping_list, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        // TODO implementation
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface ProductDataClickListener{
        void onItemChanged(ProductData item);
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        ProductViewHolder(View itemView) {
            super(itemView);
        }
    }
}
