package br.com.sunflowerstore.controller;

import br.com.sunflowerstore.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by VictorJr on 31/03/2017.
 */
@Controller
public class CustomerController {
    @RequestMapping("/clientes/novo")
    public String novo(Customer customer) {
        return "customer/CadastroCliente";
    }
}
