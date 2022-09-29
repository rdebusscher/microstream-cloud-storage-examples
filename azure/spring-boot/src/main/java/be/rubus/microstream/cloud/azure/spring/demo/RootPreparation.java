package be.rubus.microstream.cloud.azure.spring.demo;

import be.rubus.microstream.cloud.azure.spring.demo.model.Product;
import be.rubus.microstream.cloud.azure.spring.demo.model.ProductType;
import one.microstream.integrations.spring.boot.types.config.StorageManagerInitializer;
import one.microstream.storage.types.StorageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RootPreparation implements StorageManagerInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RootPreparation.class);

    @Override
    public void initialize(StorageManager storageManager) {
        LOGGER.info("(From the App) Add basic data if needed");

        // Since we have @Storage used, we are sure that Root object is initialized in StorageManager
        // We only need to check if there is an initialization of data required or not (since we already ran it before)

        Root root = (Root) storageManager.root();
        // Init 'database' with some data
        if (root.getProducts().isEmpty()) {
            init(root, storageManager);
        }

    }

    public void init(Root root, StorageManager storageManager) {
        root.setProducts(newProductList());
        // Store the modified root and its content.
        storageManager.storeRoot();
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
