package com.edutech.progressive.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Product;
import com.edutech.progressive.repository.ProductRepository;
import com.edutech.progressive.service.ProductService;

@Service
public class ProductServiceImplJpa   implements ProductService{

    private final ProductRepository productRepo;

    @Autowired
    public ProductServiceImplJpa(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    // @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<Product> getAllProducts() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getAllProducts'");
        return productRepo.findAll();
    }

    @Override
    // @PreAuthorize("hasAnyRole('USER','ADMIN')")
    // @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public Product getProductById(int productId) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getProductById'");
        return productRepo.findByProductId(productId);
    }

    @Override
    // @PreAuthorize("hasAnyRole('USER','ADMIN')")
    // @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public int addProduct(Product product) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'addProduct'");
        Product p = productRepo.save(product);
        return  p != null ? p.getProductId() :-1;
    }

    @Override
    // @PreAuthorize("hasAnyRole('USER','ADMIN')")
    // @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void updateProduct(Product product) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
        Product oldProduct = productRepo.findByProductId(product.getProductId());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setProductDescription(product.getProductDescription());
        oldProduct.setProductName(product.getProductName());
        oldProduct.setQuantity(product.getQuantity());
        oldProduct.setWarehouse(product.getWarehouse());

        productRepo.save(oldProduct);
    }

    @Override
    // @PreAuthorize("hasAnyRole('USER','ADMIN')")
    // @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void deleteProduct(int productId) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
        productRepo.deleteById(productId);
    }
    
    // @PreAuthorize("hasAnyRole('USER','ADMIN')")
    // @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public  List<Product> getAllProductByWarehouse(int warehouseId) {
        // return null;
        return productRepo.findAllByWarehouse_WarehouseId(warehouseId);
    }

} 