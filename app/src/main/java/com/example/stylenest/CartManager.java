package com.example.stylenest;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static List<Product> cartItems = new ArrayList<>();

    public static void addItem(Product product) {
        cartItems.add(product);
    }

    public static void removeItem(int position) {
        if (position >= 0 && position < cartItems.size()) {
            cartItems.remove(position);
        }
    }

    public static List<Product> getCartItems() {
        return cartItems;
    }

    public static void clearCart() {
        cartItems.clear();
    }
}
