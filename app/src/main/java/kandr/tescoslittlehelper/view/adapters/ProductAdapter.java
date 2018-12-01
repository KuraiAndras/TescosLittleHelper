package kandr.tescoslittlehelper.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
                .inflate(R.layout.item_product_list, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductData item = items.get(position);
        holder.productDataNameTextView.setText(item.name);
        holder.productDataDescriptionTextView.setText(item.description);
        holder.productDataPriceTextView.setText(String.valueOf(item.price));
        holder.addToCartButton.setEnabled(!item.inCart);

        holder.item = item;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void removeItem(ProductData item){
        int removalIndex = items.indexOf(item);
        items.remove(item);
        notifyItemInserted(removalIndex);
    }

    public void update(List<ProductData> productDataList){
        items.clear();
        items.addAll(productDataList);
        notifyDataSetChanged();
    }

    public interface ProductDataClickListener{
        void onItemChanged(ProductData item);
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        ProductData item;

        LinearLayout selectionDetails;
        TextView productDataNameTextView;
        TextView productDataDescriptionTextView;
        TextView productDataPriceTextView;
        Button deleteButton;
        Button addToCartButton;

        ProductViewHolder(View itemView) {
            super(itemView);

            initUiElements();

            selectionDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: launch details view
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    items.remove(item);
                    //TODO: delete from db
                }
            });

            addToCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: add to cart
                }
            });
        }

        private void initUiElements(){
            selectionDetails = itemView.findViewById(R.id.selectionDetails);
            productDataNameTextView = itemView.findViewById(R.id.productDataNameTextView);
            productDataDescriptionTextView = itemView.findViewById(R.id.productDataDescriptionTextView);
            productDataPriceTextView = itemView.findViewById(R.id.productDataPriceTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            addToCartButton = itemView.findViewById(R.id.addToCart);
        }
    }
}
