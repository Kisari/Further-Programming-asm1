/**
 * @author <Truong Bach Minh - s3891909>
 */
package com.example;

public abstract class Product {
    protected String name;
    protected String description;
    protected int quantity;
    protected double price;
    protected String msg;
    protected boolean isGift = false;

    public Product(String name, String description, int quantity, double price) {
        try {
            if (quantity < 0) {
                throw new IllegalArgumentException("Negative quantity number deteced! Try again");
            }
            this.name = name;
            this.description = description;
            this.quantity = quantity;
            this.price = price;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setIsGift(boolean condition) {
        this.isGift = condition;
    }

    @Override
    public String toString() {
        return "Name : \t" + this.name + "\n Description : \t" + this.description + "\n Quantity : \t" + this.quantity
                + "\nPrice : \t" + this.price;
    }

    public abstract void setMessage(String msg);

    public abstract String getMessage();

    public abstract void getInfo();

    public abstract String getType();
}
