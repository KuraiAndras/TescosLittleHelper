package kandr.tescoslittlehelper.view.adapters;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kandr.tescoslittlehelper.R;
import kandr.tescoslittlehelper.data.ProductData;
import kandr.tescoslittlehelper.services.DbManager;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> implements MyAdapter {
    private final List<ProductData> items;

    private ProductDataClickListener listener;

    public CartAdapter(ProductDataClickListener listener) {
        this.listener = listener;
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.cart_product_list, parent, false);
        return new CartAdapter.CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {
        ProductData item = items.get(position);
        holder.productDataNameTextView.setText(item.name);
        holder.productDataGtinTextView.setText(item.gtin);
        holder.productDataPriceTextView.setText(String.valueOf(item.price));
        holder.checkboxBought.setChecked(item.isBought);
        holder.item = item;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private void removeItem(ProductData item) {
        int removalIndex = items.indexOf(item);
        items.remove(item);
        notifyItemRemoved(removalIndex);
    }

    public void update(List<ProductData> productDataList) {
        items.clear();
        items.addAll(productDataList);
        notifyDataSetChanged();
    }

    private void update(ProductData productData) {
        notifyItemChanged(items.indexOf(productData));
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        ProductData item;

        LinearLayout selectionDetails;
        TextView productDataNameTextView;
        TextView productDataGtinTextView;
        TextView productDataPriceTextView;
        ImageButton cartRemoveFromCart;
        CheckBox checkboxBought;

        CartViewHolder(final View itemView) {
            super(itemView);

            initUiElements();

            selectionDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: launch details view
                }
            });

            cartRemoveFromCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.inCart = false;
                    removeItem(item);
                    DbManager.addOrUpdate(itemView.getContext(), item);
                }
            });

            checkboxBought.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: update db
                    item.isBought = !item.isBought;
                    update(item);
                    DbManager.addOrUpdate(itemView.getContext(), item);
                }
            });
        }

        private void initUiElements() {
            selectionDetails = itemView.findViewById(R.id.cartSelectionDetails);
            productDataNameTextView = itemView.findViewById(R.id.cartProductDataNameTextView);
            productDataGtinTextView = itemView.findViewById(R.id.cartProductDataGtinTextView);
            productDataPriceTextView = itemView.findViewById(R.id.cartProductDataPriceTextView);
            checkboxBought = itemView.findViewById(R.id.cartCheckboxBought);
            cartRemoveFromCart = itemView.findViewById(R.id.cartRemoveFromCart);
        }
    }
}