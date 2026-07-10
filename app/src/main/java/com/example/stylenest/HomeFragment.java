package com.example.stylenest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        setupSection(view.findViewById(R.id.rvClothing), getFashionData());
        setupSection(view.findViewById(R.id.rvToys), getToysData());
        setupSection(view.findViewById(R.id.rvAnime), getAnimeData());

        return view;
    }

    private void setupSection(RecyclerView rv, List<Product> data) {
        if (rv != null) {
            rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            rv.setAdapter(new ProductAdapter(data));
        }
    }

    private List<Product> getFashionData() {
        List<Product> list = new ArrayList<>();
        list.add(new Product("f1", "Formal Peacock Green Evening Gown", "Fashion", "KSh 12,500", R.drawable.fashion_1));
        list.add(new Product("f2", "Vintage Blouse and Flared Skirt Outfit", "Fashion", "KSh 8,200", R.drawable.fashion_2));
        list.add(new Product("f3", "Plaid Schoolgirl Outfit with Cropped Jacket", "Fashion", "KSh 6,800", R.drawable.fashion_3));
        return list;
    }

    private List<Product> getToysData() {
        List<Product> list = new ArrayList<>();
        list.add(new Product("t1", "Multi-Colored Articulated Dummy Action Figures", "Toys", "KSh 2,500", R.drawable.toy_1_1));
        list.add(new Product("t2", "Plush Anime Ball-Jointed Dolls (Blue & Pink)", "Toys", "KSh 3,500", R.drawable.toy_1_2));
        list.add(new Product("t3", "Killua Zoldyck Anime Character Figurine", "Toys", "KSh 1,200", R.drawable.toy_1_3));
        return list;
    }

    private List<Product> getAnimeData() {
        List<Product> list = new ArrayList<>();
        list.add(new Product("a1", "Chibi Anime Figurines", "Anime", "KSh 4,000", R.drawable.anime_merchandise_1));
        list.add(new Product("a2", "Custom Anime PS5 Console & Controller Skin", "Anime", "KSh 4,500", R.drawable.anime_merchandise_2));
        list.add(new Product("a3", "Anime Eye Acrylic Keychains", "Anime", "KSh 850", R.drawable.anime_merchandise_3));
        return list;
    }
}
