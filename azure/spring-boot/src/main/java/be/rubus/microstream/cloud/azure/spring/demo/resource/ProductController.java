package be.rubus.microstream.cloud.azure.spring.demo.resource;

import be.rubus.microstream.cloud.azure.spring.demo.repository.ProductRepository;
import be.rubus.microstream.cloud.azure.spring.demo.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ProductController {


    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/product")
    public Collection<Product> getAll() {
        return repository.getAll();
    }


}
