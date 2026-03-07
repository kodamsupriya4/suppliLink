package com.edutech.progressive.service;

import com.edutech.progressive.entity.Supplier;
import com.edutech.progressive.exception.SupplierDoesNotExistException;
import com.edutech.progressive.repository.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class LoginService implements UserDetailsService {

    private final SupplierRepository supplierRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(SupplierRepository supplierRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.supplierRepository = supplierRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public List<Supplier> getAllUsers() {
        return supplierRepository.findAll();
    }

    @SuppressWarnings("null")
    public Supplier getUserById(Integer userId) {
        return supplierRepository.findById(userId)
                .orElseThrow(SupplierDoesNotExistException::new);
    }

    public Supplier getSupplierByName(String username) {
        return supplierRepository.findByUsername(username)
                .orElseThrow(SupplierDoesNotExistException::new);
    }

    @Transactional
    public Supplier createUser(Supplier user) {
        
        user.setRole(normalizeRole(user.getRole()));

        
        if (supplierRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (supplierRepository.existsByEmailIgnoreCase(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

       
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        
        return supplierRepository.save(user);
    }

    @SuppressWarnings("null")
    @Transactional
    public Supplier updateUser(Supplier incoming) {
        Supplier current = supplierRepository.findById(incoming.getSupplierId())
                .orElseThrow(SupplierDoesNotExistException::new);

        // username change
        if (incoming.getUsername() != null && !incoming.getUsername().equals(current.getUsername())) {
            if (supplierRepository.existsByUsername(incoming.getUsername())) {
                throw new IllegalArgumentException("Username already exists");
            }
            current.setUsername(incoming.getUsername());
        }

        // email change
        if (incoming.getEmail() != null && !incoming.getEmail().equals(current.getEmail())) {
            if (supplierRepository.existsByEmailIgnoreCase(incoming.getEmail())) {
                throw new IllegalArgumentException("Email already exists");
            }
            current.setEmail(incoming.getEmail());
        }

        if (incoming.getSupplierName() != null) current.setSupplierName(incoming.getSupplierName());
        if (incoming.getPhone() != null)        current.setPhone(incoming.getPhone());
        if (incoming.getAddress() != null)      current.setAddress(incoming.getAddress());

        if (incoming.getRole() != null) {
            current.setRole(normalizeRole(incoming.getRole()));
        }


        if (incoming.getPassword() != null && !incoming.getPassword().isBlank()) {

            current.setPassword(passwordEncoder.encode(incoming.getPassword()));
        }

        return supplierRepository.save(current);
    }

    @SuppressWarnings("null")
    @Transactional
    public void deleteUser(Integer id) {
        if (!supplierRepository.existsById(id)) {
            throw new SupplierDoesNotExistException();
        }
        supplierRepository.deleteById(id);
    }

    // private String normalizeRole(String role) {
    //     String r = (role == null || role.isBlank()) ? "USER" : role.trim().toUpperCase();
    //     if (!r.equals("USER") && !r.equals("ADMIN")) {
    //         throw new IllegalArgumentException("Role must be USER or ADMIN");
    //     }
    //     return r;
    // }
    private String normalizeRole(String role) {
        return StringUtils.hasText(role) ? role.trim().toUpperCase() : "";
    }

    // ---------- Spring Security integration ----------

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier s = supplierRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
        // String role = "ROLE_"+s.getRole().toUpperCase();
        return User.builder()
                .username(s.getUsername())
                .password(s.getPassword()) // already encoded
                .authorities(s.getRole())
                .accountLocked(false)
                .accountExpired(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
} 