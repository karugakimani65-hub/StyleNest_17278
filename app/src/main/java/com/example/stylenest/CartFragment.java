package com.example.stylenest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Locale;

public class CartFragment extends Fragment {
    
    private RecyclerView rvCart;
    private CartAdapter adapter;
    private TextView tvEmptyCart;
    private TextView tvTotalAmount;
    private View llCheckoutSection;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        
        rvCart = view.findViewById(R.id.rvCart);
        tvEmptyCart = view.findViewById(R.id.tvEmptyCart);
        tvTotalAmount = view.findViewById(R.id.tvTotalAmount);
        llCheckoutSection = view.findViewById(R.id.llCheckoutSection);
        
        rvCart.setLayoutManager(new LinearLayoutManager(getContext()));
        
        updateCartUI();
        
        view.findViewById(R.id.btnCheckout).setOnClickListener(v -> {
            // Navigate to CheckoutFragment
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new CheckoutFragment())
                    .addToBackStack(null)
                    .commit();
        });
        
        return view;
    }

    private void updateCartUI() {
        List<Product> items = CartManager.getCartItems();
        if (items.isEmpty()) {
            rvCart.setVisibility(View.GONE);
            if (llCheckoutSection != null) llCheckoutSection.setVisibility(View.GONE);
            if (tvEmptyCart != null) tvEmptyCart.setVisibility(View.VISIBLE);
        } else {
            rvCart.setVisibility(View.VISIBLE);
            if (llCheckoutSection != null) llCheckoutSection.setVisibility(View.VISIBLE);
            if (tvEmptyCart != null) tvEmptyCart.setVisibility(View.GONE);
            
            adapter = new CartAdapter(items, () -> {
                updateCartUI();
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).updateCartBadge();
                }
            });
            rvCart.setAdapter(adapter);
            
            calculateTotal(items);
        }
    }

    private void calculateTotal(List<Product> items) {
        double total = 0;
        for (Product item : items) {
            try {
                String priceStr = item.getPrice().replace("KSh", "").replace(",", "").trim();
                total += Double.parseDouble(priceStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (tvTotalAmount != null) {
            tvTotalAmount.setText(String.format(Locale.getDefault(), "KSh %,.2f", total));
        }
    }
}
