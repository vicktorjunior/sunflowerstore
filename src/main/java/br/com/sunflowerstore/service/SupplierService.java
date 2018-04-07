package br.com.sunflowerstore.service;

import br.com.sunflowerstore.model.Supplier;
import br.com.sunflowerstore.repository.SupplierRepository;
import br.com.sunflowerstore.service.exception.SupplierAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by VictorJr on 25/04/2017.
 */
@Service
public class SupplierService {

    @Autowired
    private SupplierRepository suppliers;

    @Transactional
    public Supplier save(Supplier supplier) {
        Optional<Supplier> supplierOptional = suppliers.findByNameIgnoreCase(supplier.getName());
        if(supplierOptional.isPresent()) {
            throw new SupplierAlreadyExistsException("Nome do Supplier já existe no sistema!");
        }
        return suppliers.saveAndFlush(supplier);
    }

    public List<Supplier> listAll() {
        return suppliers.findAll();
    }

}
