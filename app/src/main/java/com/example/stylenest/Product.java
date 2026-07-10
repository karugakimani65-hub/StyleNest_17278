package com.example.stylenest;

public class Product {
    private String id;
    private String name;
    private String category;
    private String price;
    private int imageResource;

    public Product(String id, String name, String category, String price, int imageResource) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.imageResource = imageResource;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getPrice() { return price; }
    public int getImageResource() { return imageResource; }
}
