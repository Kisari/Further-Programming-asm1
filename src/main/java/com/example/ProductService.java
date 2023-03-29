/**
 * @author <Truong Bach Minh - s3891909>
 */
package com.example;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ProductService {
    private ArrayList<Product> productList;

    public ProductService() {
        this.productList = new ArrayList<Product>();
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void addProduct(Product product) {
        // check if the product has exist on the product list by looping
        for (Product productItem : productList) {
            if (productItem.getName().equals(product.getName())) {
                System.out.println("This product has exit to the product list!");
                return;
            }
        }
        ;
        // check if the product has been failed to initialized due to the constructor
        // validation
        if (product == null) {
            System.out.println("Cannot add this product to list since it is null value");
            return;
        }
        try {
            Scanner snc = new Scanner(System.in);
            System.out.println("Do you want to set this product as a gift ? Type Y or enter anthing as N");
            String choice = snc.nextLine();
            if (choice.trim().equalsIgnoreCase("Y")) {
                product.setIsGift(true);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        this.productList.add(product);
    }

    public void removeProduct(Product product) {
        this.productList.remove(product);
    }

    // create a new product method
    public void createNewProduct() {
        // initialize a new scanner
        Scanner snc = new Scanner(System.in);
        int type = 0;
        try {
            while (true) {
                // menu message
                System.out.println("----------------------------------------------------------------");
                System.out.println("1. Create a new DIGITAL product.");
                System.out.println("2. Create a new PHYSICAL product.");
                System.out.println("3. Exit");
                System.out.println("----------------------------------------------------------------");
                type = Integer.parseInt(snc.nextLine());
                // check if the input is invalid and throw an error
                if (type <= 0 || type > 3) {
                    System.out.println("Wrong input deteced! Please input the number from 1 to 3");
                    continue;
                } else {
                    // break the while if the input is correct
                    break;
                }
            }
            // input 3 meaning that Exit command is executed
            if (type == 3) {
                return;
            }
            // start to get the product information from the users input
            System.out.println("----------------------------------------------------------------");
            System.out.println("Input the name of the product : ");
            String productName = snc.nextLine();
            System.out.println("Input the desciption of the product : ");
            String productDescriptionsnc = snc.nextLine();
            System.out.println("Input the quantity of the product : ");
            int productQuantity = Integer.parseInt(snc.nextLine());
            System.out.println("Input the base price of the product : ");
            double productPrice = Double.parseDouble(snc.nextLine());
            // determine if the product is digital or physical product and create a new
            // product (added to ProductService)
            Product newProduct;
            if (type == 1) {
                newProduct = new DigitalProduct(productName, productDescriptionsnc, productQuantity,
                        productPrice);
            } else {
                System.out.println("Input the weight of the product : ");
                Double productWeight = Double.parseDouble(snc.nextLine());
                newProduct = new PhysicalProduct(productName, productDescriptionsnc, productQuantity,
                        productPrice,
                        productWeight);
            }
            if (Objects.isNull(newProduct) || newProduct.getName().equals(null)) {
                throw new IllegalArgumentException("Cannot create the product due to error");
            }
            // add the product to the ProductList with addProduct method
            this.addProduct(newProduct);
            System.out.println("----------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("Wrong input deteted! Try again");
            System.out.println(e.getMessage());
        }
    }

    // thsi method will help to edit the existing product information
    public void editProduct() {
        if (productList.size() == 0) {
            System.out.println("There is no avaiable product on the product list!");
            return;
        }
        // initialize a new scanner
        Scanner snc = new Scanner(System.in);
        try {
            // display the current products on the product list
            this.displayProductList();
            // prompt the user to input the product number to modify and check if the input
            // is invalid, throw a new error
            System.out.println("Choose your desired product to modify :");
            int productPos = Integer.parseInt(snc.nextLine());
            if (productPos < 0 || productPos > productList.size()) {
                throw new IllegalArgumentException("Please input number ranging from list above !");
            }
            // print the product by its index
            // the actual position of the product in the productList is index value so we
            // need to minus 1
            this.printProductByIndex(productPos - 1);
            // prompt the users to input the new informamtion for the product
            System.out.println("----------------------------------------------------------------");
            System.out.println("Input the desciption of the product : ");
            String productDescriptionsnc = snc.nextLine();
            System.out.println("Input the quantity of the product : ");
            int productQuantity = Integer.parseInt(snc.nextLine());
            System.out.println("Input the base price of the product : ");
            double productPrice = Double.parseDouble(snc.nextLine());
            Product newProduct;
            // Check if the product is digital or physical
            if (productList.get(productPos - 1).getType().equals("DIGITAL")) {
                newProduct = new DigitalProduct(productList.get(productPos).getName(), productDescriptionsnc,
                        productQuantity,
                        productPrice);
            } else {
                System.out.println("Input the weight of the product : ");
                Double productWeight = Double.parseDouble(snc.nextLine());
                newProduct = new PhysicalProduct(productList.get(productPos - 1)
                        .getName(), productDescriptionsnc, productQuantity,
                        productPrice, productWeight);
            }
            if (productList.get(productPos - 1).isGift) {
                System.out.println("This is a gift product, you can modify the message : ");
                String productMsg = snc.nextLine();
                newProduct.setMessage(productMsg);
            }
            productList.set(productPos - 1, newProduct);
        } catch (Exception e) {
            System.out.println("Wrong input detected! Try again");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    // this method will print the product with index + 1 (for selection)
    public void displayProductList() {
        System.out.println("----------------------------------------------------------------");
        for (int i = 0; i < productList.size(); i++) {
            System.out.println((i + 1) + "." + productList.get(i).toString());
        }
        System.out.println("----------------------------------------------------------------");
    }

    // this method will help to find and return the product object by providing its
    // product name
    public Product getProductByName(String productName) {
        for (Product product : productList) {
            if (productName.equals(product.getName())) {
                return product;
            }
        }
        System.out.println("Cannot find the product because it does not exist");
        return null;
    }

    // this will help to print out the product by providing its index
    public void printProductByIndex(int index) {
        System.out.println("Your product information: ");
        System.out.println("----------------------------------------------------------------");
        productList.get(index).getInfo();
        System.out.println("----------------------------------------------------------------");
    }

    // this method will return the product Name by prompting the user to choose
    // which product they want to pick (with displayProductList method)
    public String getTheProductNameByInput() {
        if (this.productList.size() == 0) {
            System.out.println("There is no avaiable product on the product list!");
            return null;
        }
        displayProductList();
        Scanner snc = new Scanner(System.in);
        try {
            System.out.println("Enter the product number you want :");
            int choice = Integer.parseInt(snc.nextLine());
            if (choice < 0 || choice > productList.size()) {
                throw new IllegalArgumentException("Please enter the number of product on the list above");
            }
            return productList.get(choice - 1).getName();
        } catch (Exception e) {
            System.out.println("Wrong input Type! Try again");
            System.out.println(e.getMessage());
        }
        return null;
    }

}
