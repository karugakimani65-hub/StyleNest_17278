package com.example.stylenest;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.tvName.setText(product.getName());
        holder.tvCategory.setText(product.getCategory());
        holder.tvPrice.setText(product.getPrice());
        
        if (product.getImageResource() != 0) {
            holder.ivProduct.setImageResource(product.getImageResource());
        }

        holder.btnAddToCart.setOnClickListener(v -> {
            CartManager.addItem(product);
            Toast.makeText(v.getContext(), product.getName() + " added to cart", Toast.LENGTH_SHORT).show();
            
            // Update the cart badge in MainActivity
            updateBadge(v.getContext());
        });
    }

    private void updateBadge(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof MainActivity) {
                ((MainActivity) context).updateCartBadge();
                return;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProduct;
        TextView tvName, tvCategory, tvPrice;
        Button btnAddToCart;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProduct = itemView.findViewById(R.id.ivProduct);
            tvName = itemView.findViewById(R.id.tvProductName);
            tvCategory = itemView.findViewById(R.id.tvProductCategory);
            tvPrice = itemView.findViewById(R.id.tvProductPrice);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
        }
    }
}
