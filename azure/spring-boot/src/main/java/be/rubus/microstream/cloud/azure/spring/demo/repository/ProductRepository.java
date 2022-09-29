package be.rubus.microstream.cloud.azure.spring.demo.repository;

import be.rubus.microstream.cloud.azure.spring.demo.Root;
import be.rubus.microstream.cloud.azure.spring.demo.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductRepository {

    private final Root root;

    public ProductRepository(Root root) {
        this.root = root;
    }

    public List<Product> getAll() {
        return root.getProducts();
    }


}
