/**
 * @author <Truong Bach Minh - s3891909>
 */
package com.example;

import java.util.Scanner;

public class ShoppingCart implements Comparable<ShoppingCart> {
    private String[] cart;
    private ProductService productService;

    public ShoppingCart(ProductService productService) {
        this.cart = new String[] {};
        this.productService = productService;
    }

    // setter and getter parts
    public void setCart(String[] cart) {
        this.cart = cart;
    }

    public String[] getCart() {
        return cart;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public ProductService getProductService() {
        return productService;
    }

    // override compareTo method due to the implementing Comparable interface
    @Override
    public int compareTo(ShoppingCart cart) {
        return ((int) this.getTotalWeight()) - ((int) cart.getTotalWeight());
    }

    // override toString method for using
    @Override
    public String toString() {
        return "This cart includes " + this.cart.length + ": products\t" + "Total weight :"
                + this.getTotalWeight() + "\tTotal payment : " + this.cartAmount();
    }

    // isExistOnCart help to check if the private attribute "cart" includes the
    // productName or not
    public boolean isExistOnCart(String productName) {
        for (String item : cart) {
            if (item.equals(productName)) {
                return true;
            }
        }
        return false;
    }

    // this method will help to add new product to productList
    public boolean addItem(String productName) {
        Product product = this.findProductByName(productName);
        // this if statement help to check if the product is avaiable on the
        // productService or not
        if (product == null) {
            System.out.println("Cannot find the product from the product list");
            return false;
        }
        // this if statement help to check if the product quantity equals zero and the
        // product exists
        if (product.getQuantity() == 0 || isExistOnCart(productName)) {
            System.out.println("Failed to add the product! Product out of stock or already adding to the cart");
            return false;
        }
        // create a new array that with its length increase + 1 compared to the old cart
        String[] newCart = new String[cart.length + 1];
        // copy the old cart to new cart
        for (int i = 0; i < cart.length; i++) {
            newCart[i] = cart[i];
        }
        // add the new product to the new cart
        newCart[cart.length] = productName;
        // set the new cart with setter method
        setCart(newCart);
        // decrease the current product quantity by 1
        product.setQuantity(product.getQuantity() - 1);
        // Notify the users
        System.out.println("Adding : " + productName + " to the cart");
        return true;
    }

    // this method will help to remove new product to productList
    public boolean removeItem(String productName) {
        Product product = this.findProductByName(productName);
        // this if statement help to check if the product is avaiable on the
        // productService or not
        if (product == null) {
            System.out.println("Cannot find the product from the product list");
            return false;
        }
        int removedIndex = 0;
        // check if the product exist
        if (!isExistOnCart(productName)) {
            System.out.println("Failed to add the product! Product is not on cart");
            return false;
        }
        // create a new array that with its length increase - 1 compared to the old cart
        String[] newCart = new String[cart.length - 1];
        /*
         * The first for loop will find the removed index variable
         * The second logic blocks will copy the old cart from index 0 to removed index
         * From the removed index, ensure that the next new cart element index is always
         * minor 1
         * This happens because we have removed the product from the array
         * So the product after the removed product need to be rearranged
         */
        for (int i = 0; i < cart.length; i++) {
            if (productName.equals(cart[i])) {
                removedIndex = i;
                break;
            }
        }
        for (int i = 0; i < cart.length; i++) {
            if (i == removedIndex) {
                continue;
            } else if (i > removedIndex) {
                newCart[i - 1] = cart[i];
            } else {
                newCart[i] = cart[i];
            }
        }
        // set the new cart with setter method
        setCart(newCart);
        // increase the current product quantity by 1
        product.setQuantity(product.getQuantity() + 1);
        // Notify the users
        System.out.println("Removing : " + productName + " from the cart");
        return true;
    }

    public double cartAmount() {
        double total = 0;
        // calculate the total price of the cart through loop interation
        for (Product product : productService.getProductList()) {
            if (isExistOnCart(product.getName())) {
                total += product.getPrice();
            }
        }
        // calculate the shipping fee
        double shippingFee = this.getTotalWeight() * 0.1;
        // Notify the users
        System.out.println("----------------------------------------------------------------");
        System.out.println("Your cart includes the physical products with total weight : " + this.getTotalWeight());
        System.out.println("The extra shipping fee is : " + shippingFee);
        System.out.println("The total payment : " + Double.sum(total, shippingFee));
        System.out.println("----------------------------------------------------------------");
        return Double.sum(total, shippingFee);
    }

    // this method will help to get the total weight of physical products of the
    // cart
    public double getTotalWeight() {
        double totalWeight = 0;
        for (Product product : productService.getProductList()) {
            if (product.getType().equalsIgnoreCase("PHYSIC") && isExistOnCart(product.getName())) {
                totalWeight += ((PhysicalProduct) product).getWeight();
            }
        }
        return totalWeight;
    }

    // this method will return the whole product object by providing its name
    public Product findProductByName(String productName) {
        return productService.getProductByName(productName);
    }

    // this method will return a string (Product name) based on the user input
    public String getProductNameOnCart() {
        if (this.cart.length == 0) {
            System.out.println("There is no avaiable product on the cart!");
            return null;
        }
        displayCartList();
        Scanner snc = new Scanner(System.in);
        try {
            System.out.println("Enter the product number you want :");
            int choice = Integer.parseInt(snc.nextLine());
            if (choice < 0 || choice > this.cart.length) {
                throw new IllegalArgumentException("Please enter the number of product on the list above");
            }
            ;
            return this.productService.getProductList().get(choice - 1).getName();
        } catch (Exception e) {
            System.out.println("Wrong input Type! Try again");
            System.out.println(e.getMessage());
        }
        return null;
    }

    // this method will display all the avaiable product on the current cart
    public void displayCartList() {
        System.out.println("----------------------------------------------------------------");
        for (int i = 0; i < this.cart.length; i++) {
            System.out.println((i + 1) + "." + this.productService.getProductList().get(i).toString());
        }
        System.out.println("----------------------------------------------------------------");
    }
}
