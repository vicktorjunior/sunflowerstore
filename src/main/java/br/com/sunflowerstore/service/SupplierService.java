package br.com.sunflowerstore.service;

import java.util.Optional;

import br.com.sunflowerstore.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sunflowerstore.repository.SupplierRepository;
import br.com.sunflowerstore.service.exception.NomeFornecedorJaCadastradoException;

/**
 * Created by VictorJr on 25/04/2017.
 */
@Service
public class SupplierService {

    @Autowired
    private SupplierRepository fornecedores;

    @Transactional
    public Supplier salvar(Supplier supplier) {
        Optional<Supplier> fornecedorOptional = fornecedores.findByNomeIgnoreCase(supplier.getNome());
        if(fornecedorOptional.isPresent()) {
            throw new NomeFornecedorJaCadastradoException("Nome do Supplier já existe no sistema!");
        }
        return fornecedores.saveAndFlush(supplier);
    }

}
