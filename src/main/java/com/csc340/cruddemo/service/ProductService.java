package com.csc340.cruddemo.service;

import com.csc340.cruddemo.model.Product;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author sunny
 */
@Service
public class ProductService {

    Map<String, Product> products=new HashMap<>();

    public Collection<Product> getProducts() {
        return products.values();
    }

    public Product createProduct(Product product) {
       
        products.put(product.getProductId(), product);

        return product;
    }

    public Product getProduct(String productId) {
        return products.get(productId);
    }

    public Product updateProduct(Product product) {
        Product existing = products.get(product.getProductId());
        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        products.put(product.getProductId(), existing);
        return existing;
    }

    public void deleteProduct(String productId) {
        products.remove(productId);
    }
}
