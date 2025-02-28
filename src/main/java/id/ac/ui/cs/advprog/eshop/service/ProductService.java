package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService extends ProductCreationService, ProductRetrievalService, ProductUpdateService, ProductDeleteService {
}
interface ProductCreationService {
    Product create(Product product);
}
interface ProductRetrievalService {
    List<Product> findAll();
    Product getById(String productId); // ✅ Add this method
}


interface ProductUpdateService {
    Product update(Product product);
}

interface ProductDeleteService {
    void delete(String productId);
}
