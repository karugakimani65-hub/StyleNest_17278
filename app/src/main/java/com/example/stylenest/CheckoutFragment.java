package com.example.stylenest;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Locale;

public class CheckoutFragment extends Fragment {

    private TextInputEditText etMpesaPhone, etMpesaPin, etBankAccount, etBankPassword, etAddress;
    private TextView tvCheckoutTotal;
    private Button btnConfirmPurchase;
    private static final String CHANNEL_ID = "order_notifications";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);

        etMpesaPhone = view.findViewById(R.id.etMpesaPhone);
        etMpesaPin = view.findViewById(R.id.etMpesaPin);
        etBankAccount = view.findViewById(R.id.etBankAccount);
        etBankPassword = view.findViewById(R.id.etBankPassword);
        etAddress = view.findViewById(R.id.etAddress);
        tvCheckoutTotal = view.findViewById(R.id.tvCheckoutTotal);
        btnConfirmPurchase = view.findViewById(R.id.btnConfirmPurchase);

        calculateTotal();
        createNotificationChannel();

        btnConfirmPurchase.setOnClickListener(v -> {
            if (validateFields()) {
                processPurchase();
            }
        });

        return view;
    }

    private void calculateTotal() {
        double total = 0;
        for (Product item : CartManager.getCartItems()) {
            try {
                String priceStr = item.getPrice().replace("KSh", "").replace(",", "").trim();
                total += Double.parseDouble(priceStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        tvCheckoutTotal.setText(String.format(Locale.getDefault(), "KSh %,.2f", total));
    }

    private boolean validateFields() {
        String address = etAddress.getText().toString().trim();
        if (address.isEmpty()) {
            Toast.makeText(getContext(), "Please enter a delivery address", Toast.LENGTH_SHORT).show();
            return false;
        }

        boolean hasMpesa = !etMpesaPhone.getText().toString().trim().isEmpty() && 
                          !etMpesaPin.getText().toString().trim().isEmpty();
        
        boolean hasBank = !etBankAccount.getText().toString().trim().isEmpty() && 
                         !etBankPassword.getText().toString().trim().isEmpty();

        if (!hasMpesa && !hasBank) {
            Toast.makeText(getContext(), "Please provide either M-Pesa or Bank details", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void processPurchase() {
        showOrderNotification();
        CartManager.clearCart();
        
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).updateCartBadge();
        }

        Toast.makeText(getContext(), "Purchase confirmed! Delivering to " + etAddress.getText().toString(), Toast.LENGTH_LONG).show();
        
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new HomeFragment())
                .commit();
        
        if (getActivity() instanceof MainActivity) {
            com.google.android.material.bottomnavigation.BottomNavigationView nav = getActivity().findViewById(R.id.bottom_navigation);
            nav.setSelectedItemId(R.id.nav_home);
        }
    }

    private void showOrderNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Order Successful")
                .setContentText("Your order is being processed for delivery.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) requireContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(2, builder.build());
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Order Updates", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = requireContext().getSystemService(NotificationManager.class);
            if (manager != null) manager.createNotificationChannel(channel);
        }
    }
}
