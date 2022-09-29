package be.rubus.microstream.cloud.azure.demo;

import be.rubus.microstream.cloud.azure.demo.model.Product;

import java.util.List;
import java.util.StringJoiner;

public class Root {

    private String timeStamp;
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Root.class.getSimpleName() + "[", "]")
                .add("timeStamp='" + timeStamp + "'")
                .add("products=" + products)
                .toString();
    }
}
