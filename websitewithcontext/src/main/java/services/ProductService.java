package services;

import models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    void addProduct(Product product);
}
