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

public class CategoriesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        RecyclerView rvCategories = view.findViewById(R.id.rvCategories);
        rvCategories.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Category> categories = new ArrayList<>();
        // Using real representative images for categories
        categories.add(new Category("Fashion", R.drawable.fashion_1));
        categories.add(new Category("Toys", R.drawable.toy_1_1));
        categories.add(new Category("Anime Merchandise", R.drawable.anime_merchandise_1));

        CategoryAdapter adapter = new CategoryAdapter(categories, category -> {
            CategoryDetailFragment fragment = CategoryDetailFragment.newInstance(category.getName());
            if (getActivity() != null) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        rvCategories.setAdapter(adapter);

        return view;
    }
}
