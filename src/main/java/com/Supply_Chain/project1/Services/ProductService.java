package com.Supply_Chain.project1.Services;

import com.Supply_Chain.project1.Model.Product;
import com.Supply_Chain.project1.Model.Supplier;
import com.Supply_Chain.project1.Repository.ProductRepository;
import com.Supply_Chain.project1.Repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public Product addProduct(Long supplierId, Product product) {
        Supplier supplier = supplierRepository.findById(Math.toIntExact(supplierId))
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        product.setSupplier(supplier);
        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, Product updatedProduct) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setStock(updatedProduct.getStock());
        existingProduct.setDeliveryTime(updatedProduct.getDeliveryTime());

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(productId);
    }

    public List<Product> getProductsBySupplier(Long supplierId) {
        return productRepository.findBySupplierId(supplierId);
    }
}
