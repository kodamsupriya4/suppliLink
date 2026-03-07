package com.edutech.progressive.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Product;


// public class ProductDAOImpl  implements ProductDAO{

//     final String sqlInsert = "Insert into product(warehouse_id, product_name, product_description, quantity, price) values(?,?,?,?,?)";
//     final String sqlSelect = "Select * from product";
//     final String sqlSelectById = "Select * from product where product_id=?";
//     final String sqlUpdate = "Update product set warehouse_id=?, product_name=?, product_description=?, quantity=?, price=? where product_id=?";
//     final String sqlDelete = "Delete from product where product_id=?";

//     private Connection c ;

    

//     public ProductDAOImpl() throws SQLException {
//         this.c = DatabaseConnectionManager.getConnection();
//     }

    

//     public ProductDAOImpl(Connection c) {
//         this.c = c;
//     }



//     @Override
//     public int addProduct(Product product) throws SQLException {
//         // TODO Auto-generated method stub
//         // throw new UnsupportedOperationException("Unimplemented method 'addProduct'");
//             PreparedStatement ps = c.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
//             ps.setInt(1, product.getWarehouseId());
//             ps.setString(2, product.getProductName());
//             ps.setString(3, product.getProductDescription());
//             ps.setInt(4, product.getQuantity());
//             ps.setLong(5, product.getPrice());
//             ps.executeUpdate();
//             ResultSet rs = ps.getGeneratedKeys();
//             if(rs.next()){
//                 product.setProductId(rs.getInt(1));
//                 return rs.getInt(1);
//             }
//         return -1;
//         }


        

//     @Override
//     public Product getProductById(int productId) {
//         // TODO Auto-generated method stub
//         // throw new UnsupportedOperationException("Unimplemented method 'getProductById'");
//         try{
//             PreparedStatement ps = c.prepareStatement(sqlSelectById);
//             ps.setInt(1, productId);
//             ResultSet rs= ps.executeQuery();
//             if(rs.next()){
//                 return new Product(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getLong(6));
//             }
//         }
//         catch(SQLException e){
//             e.printStackTrace();
//         }
//         return null;
//     }

//     @Override
//     public void updateProduct(Product product) {
//         // TODO Auto-generated method stub
//         // throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
//         try{
//             PreparedStatement ps = c.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
//             ps.setInt(1, product.getWarehouseId());
//             ps.setString(2, product.getProductName());
//             ps.setString(3, product.getProductDescription());
//             ps.setInt(4, product.getQuantity());
//             ps.setLong(5, product.getPrice());
//             ps.setInt(6, product.getProductId());
//             // if(ps.executeUpdate()>0) return product;
//             ps.executeUpdate();
//         }
//         catch(SQLException e){
//             e.printStackTrace();
//         }
//         // return null;

//     }

//     @Override
//     public void deleteProduct(int productId) {
//         // TODO Auto-generated method stub
//         // throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
//         try {
//             PreparedStatement ps = c.prepareStatement(sqlDelete);
//             ps.setInt(1, productId);
//             ps.executeUpdate();
//         } catch (Exception e) {
//             // TODO: handle exception
//         }
//     }

//     @Override
//     public List<Product> getAllProducts() {
//         List<Product> list = new ArrayList<>();
//         // TODO Auto-generated method stub
//         // throw new UnsupportedOperationException("Unimplemented method 'getAllProducts'");
//         try {
//             PreparedStatement ps = c.prepareStatement(sqlSelect);
//             // ps.setInt(1, productId);
//             ResultSet rs= ps.executeQuery();
//             while(rs.next()){
//                 Product p = new Product(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getLong(6));
//                 list.add(p);
//             }
//             return list;
//         } catch (Exception e) {
//             // TODO: handle exception
//             e.printStackTrace();
//         }
//         return list;
//     }
// } 