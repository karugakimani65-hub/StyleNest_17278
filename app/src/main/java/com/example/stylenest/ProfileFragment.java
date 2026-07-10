package com.example.stylenest;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView tvName = view.findViewById(R.id.tvUserName);
        TextView tvEmail = view.findViewById(R.id.tvUserEmail);
        Button btnEditProfile = view.findViewById(R.id.btnEditProfile);
        Button btnOrderHistory = view.findViewById(R.id.btnOrderHistory);
        Button btnLogout = view.findViewById(R.id.btnLogout);

        // Set User Info
        tvName.setText("James Karuga");
        tvEmail.setText("karugakimani54@gmail.com");

        btnEditProfile.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Edit Profile feature coming soon!", Toast.LENGTH_SHORT).show();
        });

        btnOrderHistory.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Order History is empty", Toast.LENGTH_SHORT).show();
        });

        btnLogout.setOnClickListener(v -> {
            // Clear cart data (reset to default)
            CartManager.clearCart();
            Toast.makeText(getContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();
            
            // Navigate back to Splash Screen (Restart app flow)
            if (getActivity() != null) {
                Intent intent = new Intent(getActivity(), SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}
