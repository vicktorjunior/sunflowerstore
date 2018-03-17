package br.com.sunflowerstore.controller;

import br.com.sunflowerstore.model.ItemSell;
import br.com.sunflowerstore.model.Sell;
import br.com.sunflowerstore.service.ProductService;
import br.com.sunflowerstore.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/sell/")
public class SellController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SellService sellService;

    private Sell sell = new Sell();

    public SellController(ProductService productService) {
		this.productService= productService;
	}

	@RequestMapping("new")
    public ModelAndView novo(ItemSell itemSell) {
        ModelAndView mv = new ModelAndView("sell/new");
        mv.addObject("produtos", productService.listInStock());
        return mv;
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("itemSell")@Valid ItemSell itemSell, BindingResult result,
                            RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            System.out.println("errou!");
            return novo(itemSell);
        }

            sellService.add(itemSell, sell);



        return new ModelAndView("redirect:/sell/new");

        //Product product = productService.get();
        //sellService.add(product, sell);

        //System.out.println(sell.toString());
    }



}
