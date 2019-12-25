package services;

import models.Product;
import repositories.ProductRepository;
import ru.itis.Component;

import java.util.List;

public class ProductServiceImpl implements ProductService, Component {

    ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }


    @Override
    public String getComponentName() {
        return "productServiceImpl";
    }
}
