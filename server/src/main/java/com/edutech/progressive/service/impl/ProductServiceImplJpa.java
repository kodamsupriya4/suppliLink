package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Product;
import com.edutech.progressive.entity.Warehouse;
import com.edutech.progressive.exception.InsufficientCapacityException;
import com.edutech.progressive.repository.ProductRepository;
import com.edutech.progressive.repository.WarehouseRepository;
import com.edutech.progressive.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service("productServiceImplJpa")
public class ProductServiceImplJpa implements ProductService {

    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;   // may be null in 1-arg ctor

    // ✅ Optional field injection for ShipmentRepository to avoid context failures
    @Autowired(required = false)
    private com.edutech.progressive.repository.ShipmentRepository shipmentRepository;

    // Preferred constructor (as earlier)
    @Autowired
    public ProductServiceImplJpa(ProductRepository productRepository,
                                 WarehouseRepository warehouseRepository) {
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
    }

    // Backward-compatible constructor used by some unit tests
    public ProductServiceImplJpa(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.warehouseRepository = null;
    }

    @Override
    public List<Product> getAllProducts() throws SQLException {
        try {
            return new ArrayList<>(productRepository.findAll());
        } catch (DataAccessException ex) {
            throw new SQLException("Failed to fetch products", ex);
        }
    }

    @Override
    public Product getProductById(int productId) throws SQLException {
        try {
            return productRepository.findByProductId(productId);
        } catch (DataAccessException ex) {
            throw new SQLException("Failed to fetch product id: " + productId, ex);
        }
    }

    @Override
    public int addProduct(Product product) throws SQLException {
        try {
            // Day 9 capacity check (only if WarehouseRepository is available)
            if (warehouseRepository != null) {
                int warehouseId = product.getWarehouseId();
                if (warehouseId == 0 && product.getWarehouse() != null) {
                    warehouseId = product.getWarehouse().getWarehouseId();
                }
                Warehouse wh = warehouseRepository.findByWarehouseId(warehouseId);
                if (wh == null) {
                    throw new SQLException("Warehouse not found for id: " + warehouseId);
                }
                int currentCount = productRepository.countByWarehouse_WarehouseId(warehouseId);
                if (currentCount >= wh.getCapacity()) {
                    throw new InsufficientCapacityException(
                            "Warehouse capacity reached for warehouseId=" + warehouseId);
                }
            }
            return productRepository.save(product).getProductId();
        } catch (InsufficientCapacityException ice) {
            throw ice;
        } catch (DataAccessException ex) {
            throw new SQLException("Failed to add product", ex);
        }
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        try {
            productRepository.save(product);
        } catch (DataAccessException ex) {
            throw new SQLException("Failed to update product id: " + product.getProductId(), ex);
        }
    }

    @Override
    public void deleteProduct(int productId) throws SQLException {
        try {
            // Day 10: delete shipments first IF repo is available
            if (shipmentRepository != null) {
                shipmentRepository.deleteByProductId(productId);
            }
            productRepository.deleteById(productId);
        } catch (DataAccessException ex) {
            throw new SQLException("Failed to delete product id: " + productId, ex);
        }
    }

    @Override
    public List<Product> getAllProductByWarehouse(int warehouseId) throws SQLException {
        try {
            // FK-based first
            List<Product> byFk = productRepository.findAllByWarehouseId(warehouseId);
            if (byFk != null && !byFk.isEmpty()) return new ArrayList<>(byFk);

            // Association-based fallback
            List<Product> byAssoc = productRepository.findAllByWarehouse_WarehouseId(warehouseId);
            if (byAssoc != null && !byAssoc.isEmpty()) return new ArrayList<>(byAssoc);

            // JDBC DAO fallback
            com.edutech.progressive.dao.ProductDAO jdbcDao = new com.edutech.progressive.dao.ProductDAOImpl();
            List<Product> all = jdbcDao.getAllProducts();
            List<Product> filtered = new ArrayList<>();
            for (Product p : all) if (p.getWarehouseId() == warehouseId) filtered.add(p);
            return filtered;

        } catch (DataAccessException ex) {
            throw new SQLException("Failed to fetch products for warehouse id: " + warehouseId, ex);
        }
    }
} 