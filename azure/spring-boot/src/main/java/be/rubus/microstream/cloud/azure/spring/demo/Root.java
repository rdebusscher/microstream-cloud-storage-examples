package be.rubus.microstream.cloud.azure.spring.demo;

import be.rubus.microstream.cloud.azure.spring.demo.model.Product;
import one.microstream.integrations.spring.boot.types.Storage;

import java.util.ArrayList;
import java.util.List;

@Storage
public class Root {

    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
