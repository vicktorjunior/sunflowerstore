package br.com.sunflowerstore.controller;

import br.com.sunflowerstore.model.ItemSell;
import br.com.sunflowerstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sell/")
public class SellController {

    @Autowired
    private ProductService productService;

    public SellController(ProductService productService) {
		this.productService= productService;
	}

	@RequestMapping("new")
    public ModelAndView novo(ItemSell itemSell) {
        ModelAndView mv = new ModelAndView("sell/new");
        mv.addObject("produtos", productService.listInStock());
        return mv;
    }

    @RequestMapping("add")
    public void add(){

    }



}
