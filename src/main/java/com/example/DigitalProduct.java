/**
 * @author <Truong Bach Minh - s3891909>
 */
package com.example;

public class DigitalProduct extends Product {
    private final String TYPE = "DIGITAL";

    public DigitalProduct(String name, String description, int quantity, double price) {
        super(name, description, quantity, price);
    }

    public void setMessage(String msg) {
        this.msg = msg;
    };

    public String getMessage() {
        return msg.toString();
    };

    @Override
    public String toString() {
        return "DIGITAL - " + this.getName();
    }

    public void getInfo() {
        System.out.println(
                "Name : \t" + this.name + "\nDescription : \t" + this.description + "\nQuantity : \t" + this.quantity
                        + "\nPrice : \t" + this.price);
    }

    public String getType() {
        return this.TYPE;
    }
}
