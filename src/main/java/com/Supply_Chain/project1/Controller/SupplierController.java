package com.Supply_Chain.project1.Controller;

import com.Supply_Chain.project1.Model.Supplier;
import com.Supply_Chain.project1.Services.SupplierServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    @Autowired
    private SupplierServices supplierServices;

    @PostMapping("/register")
    public ResponseEntity<Supplier> registerSupplier(@RequestBody Supplier supplier) {
        Supplier savedSupplier = supplierServices.registerSupplier(supplier);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSupplier);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplier(@PathVariable Long id) {
        Supplier supplier = supplierServices.getSupplierById(id);
        return ResponseEntity.ok(supplier);
    }

    @GetMapping("/")
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        List<Supplier> suppliers = supplierServices.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }
}
