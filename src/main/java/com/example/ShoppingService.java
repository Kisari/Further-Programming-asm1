/**
 * @author <Truong Bach Minh - s3891909>
 */
package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ShoppingService {
    private ProductService productService = new ProductService();
    private ShoppingCart shoppingCart = new ShoppingCart(productService);
    private ArrayList<ShoppingCart> ShoppingCartList = new ArrayList<ShoppingCart>();

    public ShoppingService() {

    }

    public void run() {
        Scanner snc = new Scanner(System.in);
        welcomeMessage();
        while (true) {
            try {
                this.menuMessage();
                System.out.println("Input your option: ");
                // int choice = 0;

                int choice = Integer.parseInt(snc.nextLine());
                switch (choice) {
                    case 1:
                        // Create a new product
                        this.shoppingCart.getProductService().createNewProduct();
                        break;
                    case 2:
                        // Edit product information
                        this.shoppingCart.getProductService().editProduct();
                        for (ShoppingCart element : ShoppingCartList) {
                            element.setProductService(this.shoppingCart.getProductService());
                        }
                        break;
                    case 3:
                        // Create a new shopping cart
                        this.productService = shoppingCart.getProductService();
                        if (!this.ShoppingCartList.contains(this.shoppingCart)) {
                            this.ShoppingCartList.add(this.shoppingCart);
                        }
                        this.shoppingCart = new ShoppingCart(productService);
                        break;
                    case 4:
                        // Add the product to the current shopping cart
                        String addedProductName = this.shoppingCart.getProductService().getTheProductNameByInput();
                        // Testing
                        System.out.println("--------------------------------------------------");
                        System.out.println("You get 1 " + addedProductName + " from the Shop");
                        this.shoppingCart.addItem(addedProductName);
                        // Testing
                        System.out.println("--------------------------------------------------");
                        System.out.println("Remaining quantities : " +
                                this.shoppingCart.getProductService().getProductByName(addedProductName).getQuantity());
                        break;
                    case 5:
                        // Remove the product from the current shopping cart
                        String removedProductName = this.shoppingCart.getProductNameOnCart();
                        System.out.println("--------------------------------------------------");
                        System.out.println("You return 1 " + removedProductName + " to the Shop");
                        this.shoppingCart.removeItem(removedProductName);
                        // Testing
                        System.out.println("--------------------------------------------------");
                        System.out.println("Remaining quantities : " +
                                this.shoppingCart.getProductService().getProductByName(
                                        removedProductName).getQuantity());
                        break;
                    case 6:
                        // Display the cart amount
                        this.shoppingCart.cartAmount();
                        break;
                    case 7:
                        if (!this.ShoppingCartList.contains(this.shoppingCart)) {
                            this.ShoppingCartList.add(this.shoppingCart);
                        }
                        Collections.sort(ShoppingCartList);
                        for (ShoppingCart shoppingCart : ShoppingCartList) {
                            System.out.println(shoppingCart.toString());
                            ;
                        }
                        break;
                    case 8:
                        System.exit(0);
                        break;
                    default:
                        throw new IllegalArgumentException("Please input number from 1 to 8");
                }
            } catch (Exception e) {
                System.out.println("Wrong input! Try again");
                System.out.println(e.getMessage());
            }
        }
    }

    public void welcomeMessage() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Course ID: COSC2440 | Further Programming \n");
        System.out.println("Lecturer: Tri Dang Tran \n");
        System.out.println("Assessment name: Individual Project \n");
        System.out.println("Author name: Truong Bach Minh \n");
        System.out.println("Student ID: s3891909 \n");
        System.out.println("----------------------------------------------------------------");
    }

    public void menuMessage() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("1. Create a new product");
        System.out.println("2. Edit product information");
        System.out.println("3. Create a new shopping cart");
        System.out.println("4. Add the product to the current shopping cart");
        System.out.println("5. Remove the product from the current shopping cart");
        System.out.println("6. Display the cart amount");
        System.out.println("7. Display all shopping carts based on their total weight");
        System.out.println("9. Exit");
        System.out.println("----------------------------------------------------------------");
    }
}
