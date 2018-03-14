package br.com.sunflowerstore.controller;

import br.com.sunflowerstore.model.Sell;
import br.com.sunflowerstore.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sell/")
public class SellController {

    private ProductRepository productRepository;


    @RequestMapping("new")
    public ModelAndView novo(Sell sell) {
        ModelAndView mv = new ModelAndView("sell/new");
        mv.addObject("produtos", productRepository.findAll());
        return mv;
    }

}
