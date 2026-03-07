package com.edutech.progressive.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Supplier;


@Repository
public class SupplierDAOImpl implements SupplierDAO{

    private Connection c;

    final String sqlInsert = "Insert into supplier(supplier_name, email, username, password, phone, address, role) values(?,?,?,?,?,?,?)";
    final String sqlSelect = "Select * from supplier";
    final String sqlSelectById = "Select * from supplier where supplier_id=?";
    final String sqlUpdate = "Update supplier set supplier_name=?, email=?, username=?, password=?, phone=?, address=?, role=? where supplier_id=?";
    final String sqlDelete = "Delete from supplier where supplier_id=?";


    

    public SupplierDAOImpl() throws SQLException {
        this.c = DatabaseConnectionManager.getConnection();
    }



    public SupplierDAOImpl(Connection c) {
        this.c = c;
    }



    @Override
    public int addSupplier(Supplier supplier) throws SQLException {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'addSupplier'");
        PreparedStatement ps = c.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getEmail());
            ps.setString(3, supplier.getUsername());
            ps.setString(4, supplier.getPassword());
            ps.setString(5, supplier.getPhone());
            ps.setString(6, supplier.getAddress());
            ps.setString(7, supplier.getRole());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                supplier.setSupplierId(rs.getInt(1));
                return rs.getInt(1);
            }
        return -1;
    }



    @Override
    public Supplier getSupplierById(int supplierId) throws SQLException {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getSupplierById'");
        PreparedStatement ps = c.prepareStatement(sqlSelectById);
            ps.setInt(1, supplierId);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                return new Supplier(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
            }
        return null;
    }



    @Override
    public void updateSupplier(Supplier supplier) throws SQLException {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'updateSupplier'");
        PreparedStatement ps = c.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getEmail());
            ps.setString(3, supplier.getUsername());
            ps.setString(4, supplier.getPassword());
            ps.setString(5, supplier.getPhone());
            ps.setString(6, supplier.getAddress());
            ps.setString(7, supplier.getRole());
            ps.setInt(8, supplier.getSupplierId());
            ps.executeUpdate();

            // return (ps.executeUpdate()>0)? supplier.getSupplierId():-1;
    }



    @Override
    public void deleteSupplier(int supplierId) throws SQLException {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'deleteSupplier'");
        PreparedStatement ps = c.prepareStatement(sqlDelete);
            ps.setInt(1, supplierId);
            ps.executeUpdate();
    }



    @Override
    public List<Supplier> getAllSuppliers() throws SQLException {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getAllSuppliers'");
        List<Supplier> list = new ArrayList<>();
        PreparedStatement ps = c.prepareStatement(sqlSelect);
            // ps.setInt(1, productId);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Supplier p = new Supplier(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                list.add(p);
            }
        return list;
    }
} 