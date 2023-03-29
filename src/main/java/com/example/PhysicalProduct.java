/**
 * @author <Truong Bach Minh - s3891909>
 */
package com.example;

public class PhysicalProduct extends Product {
    private double weight;
    private final String TYPE = "PHYSIC";

    public PhysicalProduct(String name, String description, int quantity, double price, double weight) {
        super(name, description, quantity, price);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setMessage(String msg) {
        this.msg = msg;
    };

    public String getMessage() {
        return msg.toString();
    };

    @Override
    public String toString() {
        return "PHYSIC - " + this.getName();
    }

    public void getInfo() {
        System.out.println(
                "Name : \t" + this.name + "\nDescription : \t" + this.description + "\nQuantity : \t" + this.quantity
                        + "\nPrice : \t" + this.price + "\nWeight : \t" + this.weight);
    }

    public String getType() {
        return this.TYPE;
    }
}
