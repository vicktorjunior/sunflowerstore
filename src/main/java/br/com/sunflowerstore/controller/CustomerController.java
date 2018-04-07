package br.com.sunflowerstore.controller;

import br.com.sunflowerstore.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by VictorJr on 31/03/2017.
 */
@Controller
@RequestMapping("/customer/")
public class CustomerController {
    @RequestMapping("new")
    public String newCustomer(Customer customer) {
        return "customer/new";
    }
}
