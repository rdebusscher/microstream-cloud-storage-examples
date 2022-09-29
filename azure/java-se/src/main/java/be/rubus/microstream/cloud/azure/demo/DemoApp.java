package be.rubus.microstream.cloud.azure.demo;

import be.rubus.microstream.cloud.azure.demo.model.Product;
import be.rubus.microstream.cloud.azure.demo.model.ProductType;
import one.microstream.storage.types.StorageManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DemoApp {
    public static void main(String[] args) {
        // Application-specific root instance
        Root root = new Root();

        //StorageManager storageManager = StorageManagerFactory.defineStorageManagerProgrammatically(root);
        StorageManager storageManager = StorageManagerFactory.defineStorageManagerDeclarative(root);

        // print the root to show its loaded content (stored in the last execution).
        System.out.println(root);

        root.setTimeStamp(new Date().toString());

        if (root.getProducts() == null) {
            root.setProducts(newProductList());
            // Store the modified root and its content.
            storageManager.storeRoot();
        } else {
            if (root.getProducts().size() == 4) {
                root.getProducts().add(newProduct());
                // Store what is changed!!
                storageManager.store(root.getProducts());
            }
        }

        storageManager.shutdown();

    }

    private static Product newProduct() {
        return new Product(5, "Pizza", ProductType.ENTERTAINMENT);
    }

    private static List<Product> newProductList() {

        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Bread", ProductType.FOOD));
        products.add(new Product(2, "Orange Juice", ProductType.FOOD));
        products.add(new Product(3, "Floor cleaning", ProductType.CLEANING));
        products.add(new Product(4, "Cinema Tickets", ProductType.ENTERTAINMENT));
        return products;

    }
}
