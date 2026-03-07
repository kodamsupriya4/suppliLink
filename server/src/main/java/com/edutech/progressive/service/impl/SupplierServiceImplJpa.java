package com.edutech.progressive.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// import com.edutech.progressive.config.SecurityConfig;
import com.edutech.progressive.entity.Supplier;
import com.edutech.progressive.exception.SupplierAlreadyExistsException;
import com.edutech.progressive.exception.SupplierDoesNotExistException;
import com.edutech.progressive.repository.SupplierRepository;
import com.edutech.progressive.service.SupplierService;

@Service("supplierServiceJpa")
public class SupplierServiceImplJpa implements SupplierService  {

    private SupplierRepository supplierRepo;
    private PasswordEncoder encoder;
    

    @Autowired
    public SupplierServiceImplJpa(SupplierRepository supplierRepo, PasswordEncoder pe) {
        this.encoder = pe;
        this.supplierRepo = supplierRepo;
    }


    


    

    
    @Override
    @Secured({"ROLE_ADMIN"})
    public int addSupplier(Supplier supplier) throws SupplierAlreadyExistsException {
        // If client sends an id that already exists, treat as duplicate
        if (supplier.getSupplierId() != 0 &&
            supplierRepo.findById(supplier.getSupplierId()).orElse(null) != null) {
            throw new SupplierAlreadyExistsException("Supplier already exists");
        }

        // Enforce uniqueness on email / username
        boolean dup = supplierRepo.existsByEmailIgnoreCase(supplier.getEmail())
                   || supplierRepo.existsByUsername(supplier.getUsername());
        if (dup) throw new SupplierAlreadyExistsException("Supplier already exists");
        supplier.setPassword(encoder.encode(supplier.getPassword()));
        Supplier saved = supplierRepo.save(supplier);
        return saved != null ? saved.getSupplierId() : -1;
    }
    




    @Override
        
    public List<Supplier> getAllSuppliers() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getAllSuppliers'");
        return supplierRepo.findAll();
    }



    


    @Override
        
    public List<Supplier> getAllSuppliersSortedByName() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getAllSuppliersSortedByName'");
        List<Supplier> list = supplierRepo.findAll();
        Collections.sort(list);
        return list;
    }

    
public void updateSupplier(int id, Supplier s)
            throws SupplierAlreadyExistsException, SupplierDoesNotExistException {

        Supplier old = supplierRepo.findById(id)
                .orElseThrow(() -> new SupplierDoesNotExistException("Supplier not found: " + id));


        boolean dupEmail = supplierRepo.existsByEmailIgnoreCaseAndSupplierIdNot(s.getEmail(), id);
        boolean dupUser  = supplierRepo.existsByUsernameIgnoreCaseAndSupplierIdNot(s.getUsername(), id);
        if (dupEmail || dupUser) {
            throw new SupplierAlreadyExistsException("Supplier already exists");
        }

        old.setAddress(s.getAddress());
        old.setEmail(s.getEmail());
        old.setPassword(s.getPassword());
        old.setUsername(s.getUsername());
        old.setPhone(s.getPhone());
        old.setRole(s.getRole());
        old.setSupplierName(s.getSupplierName());
        supplierRepo.save(old);
    }


    public void deleteSupplier(int id) throws SupplierDoesNotExistException{
        Supplier s = supplierRepo.findById(id).orElseThrow();
        if(s==null) throw new SupplierDoesNotExistException("Supplier does not exists");
        supplierRepo.deleteById(id);

    }
        
    public Supplier getSupplierById(int supplierId) throws SupplierDoesNotExistException {
        Supplier s = supplierRepo.findById(supplierId).orElseThrow();
        if(s==null) throw new SupplierDoesNotExistException("Supplier does not exists");
        return supplierRepo.findById(supplierId).orElseThrow();
    }



}

// package com.edutech.progressive.service.impl;

// import java.util.List;

// import org.springframework.data.domain.Sort;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import com.edutech.progressive.entity.Supplier;
// import com.edutech.progressive.exception.SupplierDoesNotExistException;
// import com.edutech.progressive.repository.SupplierRepository;
// import com.edutech.progressive.service.SupplierService;

// @Service
// public class SupplierServiceImplJpa implements SupplierService {

//     private final SupplierRepository repo;
//     private final PasswordEncoder encoder;

//     public SupplierServiceImplJpa(SupplierRepository repo, PasswordEncoder encoder) {
//         this.repo = repo;
//         this.encoder = encoder;
//     }

//     @Override
//     public List<Supplier> getAllSuppliers() {
//         return repo.findAll();
//     }

//     @Override
//     public List<Supplier> getAllSuppliersSortedByName() {
//         return repo.findAll(Sort.by(Sort.Direction.ASC, "supplierName"));
//     }

//     @Override
//     public Supplier getSupplierById(int supplierId) {
//         return repo.findById(supplierId)
//                    .orElseThrow(() -> new SupplierDoesNotExistException("Supplier id " + supplierId + " not found"));
//     }

//     @Override
//     @Transactional
//     public int addSupplier(Supplier s) {
//         // normalizeAndValidateRole(s);

//         // uniqueness
//         if (repo.existsByUsername(s.getUsername()))
//             throw new com.edutech.progressive.exception.SupplierAlreadyExistsException("Username already taken");
//         if (repo.existsByEmailIgnoreCase(s.getEmail()))
//             throw new com.edutech.progressive.exception.SupplierAlreadyExistsException("Email already registered");

//         // encode password
//         s.setPassword(encoder.encode(s.getPassword()));

//         Supplier saved = repo.save(s);
//         return saved.getSupplierId();
//     }

//     // @Override
//     @Transactional
//     public void updateSupplier( int supplierId, Supplier incoming)  {
//         Supplier current = getSupplierById(incoming.getSupplierId());

//         // username changed?
//         if (incoming.getUsername() != null && !incoming.getUsername().equals(current.getUsername())) {
//             if (repo.existsByUsername(incoming.getUsername()))
//                 throw new com.edutech.progressive.exception.SupplierAlreadyExistsException("Username already taken");
//             current.setUsername(incoming.getUsername());
//         }

//         // email changed?
//         if (incoming.getEmail() != null && !incoming.getEmail().equals(current.getEmail())) {
//             if (repo.existsByEmailIgnoreCase(incoming.getEmail()))
//                 throw new com.edutech.progressive.exception.SupplierAlreadyExistsException("Email already registered");
//             current.setEmail(incoming.getEmail());
//         }

//         if (incoming.getSupplierName() != null) current.setSupplierName(incoming.getSupplierName());
//         if (incoming.getPhone() != null) current.setPhone(incoming.getPhone());
//         if (incoming.getAddress() != null) current.setAddress(incoming.getAddress());


//         if (incoming.getRole() != null) {
//             current.setRole(incoming.getRole()); // will throw if invalid
//         }

//         // password – only if provided and different from existing
//         if (incoming.getPassword() != null && !incoming.getPassword().isBlank()) {
//             boolean same = encoder.matches(incoming.getPassword(), current.getPassword());
//             if (!same) {
//                 current.setPassword(encoder.encode(incoming.getPassword()));
//             }
//         }

//         repo.save(current);
//     }

//     @Override
//     @Transactional
//     public void deleteSupplier(int supplierId) {
//         // If you have dependent tables (products/addresses), delete them here first (cascade or explicit)
//         if (!repo.existsById(supplierId))
//             throw new SupplierDoesNotExistException("Supplier id " + supplierId + " not found");
//         repo.deleteBySupplierId(supplierId);
//     }

//     // ---------- helpers ----------


// } 