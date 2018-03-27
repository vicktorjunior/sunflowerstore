package br.com.sunflowerstore.controller;

import br.com.sunflowerstore.model.ItemSell;
import br.com.sunflowerstore.model.Sell;
import br.com.sunflowerstore.service.ProductService;
import br.com.sunflowerstore.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
@RequestMapping("/sell/")
public class SellController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SellService sellService;



    public SellController(ProductService productService) {
		this.productService= productService;
	}

	@RequestMapping("new")
    public ModelAndView novo(ItemSell itemSell) {
        /*List<ItemSell> items = new ArrayList<>();
        items.add(new ItemSell());*/



        ModelAndView mv = new ModelAndView("sell/new");


        mv.addObject("produtos", productService.listInStock());
        mv.addObject("itemSell",new ItemSell());
        mv.addObject("sell", new Sell());
        return mv;
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    @ResponseBody
    public void add(@Valid ItemSell itemSell, BindingResult result,
                            RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            System.out.println("errou!");
          //  return novo(itemSell);
        }

        itemSell.setProduct(productService.get(itemSell.getProduct().getCodigo()));

            sellService.add(itemSell);



        //return new ModelAndView("redirect:/sell/new");

        //Product product = productService.get();
        //sellService.add(product, sell);

        //System.out.println(sell.toString());
    }


    @RequestMapping(value = "changeValue", method = RequestMethod.POST)
    @ResponseBody
    public BigDecimal changeValue(ItemSell itemSell) {
        return itemSell.getUnitValue();

    }



}
