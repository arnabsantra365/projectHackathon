package com.Supply_Chain.project1.Service;

import com.Supply_Chain.project1.Model.Order;
import com.Supply_Chain.project1.Model.Product;
import com.Supply_Chain.project1.Model.Supplier;
import com.Supply_Chain.project1.Repository.OrderRepository;
import com.Supply_Chain.project1.Repository.ProductRepository;
import com.Supply_Chain.project1.Repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, SupplierRepository supplierRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    public Order createOrder(String customerName, Long productId, int quantity) {
        // Get all suppliers that provide this product
        List<Product> availableProducts = productRepository.findAll()
                .stream()
                .filter(p -> p.getId().equals(productId) && p.getStock() >= quantity)
                .sorted(Comparator.comparingDouble(Product::getPrice)
                        .thenComparingInt(Product::getDeliveryTime)) // Cheapest and fastest first
                .toList();

        if (availableProducts.isEmpty()) {
            throw new RuntimeException("No supplier available with enough stock");
        }

        // Select the best supplier
        Product bestProduct = availableProducts.get(0);
        Supplier bestSupplier = bestProduct.getSupplier();

        // Create and save the order
        Order order = new Order();
        order.setCustomerName(customerName);
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setStatus("Pending");
        order.setSupplier(bestSupplier);

        // Reduce stock
        bestProduct.setStock(bestProduct.getStock() - quantity);
        productRepository.save(bestProduct);

        return orderRepository.save(order);
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getOrdersBySupplier(Long supplierId) {
        return orderRepository.findAll()
                .stream()
                .filter(order -> order.getSupplier().getId().equals(supplierId))
                .toList();
    }

    public Order updateOrderStatus(Long id, String status) {
        return orderRepository.findById(id).map(order -> {
            order.setStatus(status);
            return orderRepository.save(order);
        }).orElseThrow(() -> new RuntimeException("Order not found"));
    }
}