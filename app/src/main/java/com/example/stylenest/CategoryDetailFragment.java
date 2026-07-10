package com.example.stylenest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CategoryDetailFragment extends Fragment {

    private static final String ARG_CATEGORY_NAME = "category_name";
    private String categoryName;

    public static CategoryDetailFragment newInstance(String categoryName) {
        CategoryDetailFragment fragment = new CategoryDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY_NAME, categoryName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryName = getArguments().getString(ARG_CATEGORY_NAME);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_detail, container, false);

        TextView tvTitle = view.findViewById(R.id.tvCategoryTitle);
        tvTitle.setText(categoryName);

        RecyclerView rvItems = view.findViewById(R.id.rvCategoryItems);
        rvItems.setLayoutManager(new GridLayoutManager(getContext(), 2));

        ProductAdapter adapter = new ProductAdapter(getFilteredProducts(categoryName));
        rvItems.setAdapter(adapter);

        return view;
    }

    private List<Product> getFilteredProducts(String category) {
        List<Product> allProducts = new ArrayList<>();
        // Fashion
        allProducts.add(new Product("f1", "Formal Peacock Green Evening Gown", "Fashion", "KSh 12,500", R.drawable.fashion_1));
        allProducts.add(new Product("f2", "Vintage Blouse and Flared Skirt Outfit", "Fashion", "KSh 8,200", R.drawable.fashion_2));
        allProducts.add(new Product("f3", "Plaid Schoolgirl Outfit with Cropped Jacket", "Fashion", "KSh 6,800", R.drawable.fashion_3));
        allProducts.add(new Product("f4", "Formal Waistcoat, Shirt, and Tie Outfit", "Fashion", "KSh 5,500", R.drawable.fashion_4));
        allProducts.add(new Product("f5", "Dark Winter Trench Coat", "Fashion", "KSh 9,000", R.drawable.fashion_5));
        
        // Toys
        allProducts.add(new Product("t1", "Multi-Colored Articulated Dummy Action Figures", "Toys", "KSh 2,500", R.drawable.toy_1_1));
        allProducts.add(new Product("t2", "Plush Anime Ball-Jointed Dolls (Blue & Pink)", "Toys", "KSh 3,500", R.drawable.toy_1_2));
        allProducts.add(new Product("t3", "Killua Zoldyck Anime Character Figurine", "Toys", "KSh 4,200", R.drawable.toy_1_3));
        allProducts.add(new Product("t4", "Assorted Toddler Toys & Educational Flat Lay Grid", "Toys", "KSh 3,800", R.drawable.toy_1_4));
        allProducts.add(new Product("t5", "Remote Controlled Drone with Screen Controller", "Toys", "KSh 15,000", R.drawable.toy_1_5));
        
        // Anime
        allProducts.add(new Product("a1", "Chibi Anime Figurines", "Anime Merchandise", "KSh 4,000", R.drawable.anime_merchandise_1));
        allProducts.add(new Product("a2", "Custom Anime PS5 Console & Controller Skin", "Anime Merchandise", "KSh 4,500", R.drawable.anime_merchandise_2));
        allProducts.add(new Product("a3", "Anime Eye Acrylic Keychains", "Anime Merchandise", "KSh 850", R.drawable.anime_merchandise_3));
        allProducts.add(new Product("a4", "Chibi Anime Throw Pillow", "Anime Merchandise", "KSh 2,200", R.drawable.anime_merchandise_4));
        allProducts.add(new Product("a5", "Anime Desk Accessory / Mousepad", "Anime Merchandise", "KSh 1,500", R.drawable.anime_merchandise_5));

        List<Product> filtered = new ArrayList<>();
        for (Product p : allProducts) {
            if (p.getCategory().equalsIgnoreCase(category) || (category.contains("Anime") && p.getCategory().contains("Anime"))) {
                filtered.add(p);
            }
        }
        return filtered;
    }
}
