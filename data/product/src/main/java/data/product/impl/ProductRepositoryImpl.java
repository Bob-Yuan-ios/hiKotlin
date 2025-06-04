package data.product.impl;

import java.util.Arrays;
import java.util.List;

import domain.product.model.Product;
import domain.product.respository.ProductRepository;

public class ProductRepositoryImpl implements ProductRepository {

    /**
     */
    @Override
    public List<Product> getProductList() {

        return Arrays.asList(
                new Product("iPhone 15", 999.99),
                new Product("Samsung Galaxy S24", 899.99),
                new Product("Xiaomi 14 Pro", 699.99)
        );
    }
}
