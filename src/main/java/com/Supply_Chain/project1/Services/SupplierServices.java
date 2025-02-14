package com.Supply_Chain.project1.Services;

import com.Supply_Chain.project1.Model.Supplier;
import com.Supply_Chain.project1.Repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SupplierServices {
    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier registerSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier getSupplierById(Long supplierId) {
        return supplierRepository.findById(Math.toIntExact(supplierId))
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }
}
