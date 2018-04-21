package br.com.sunflowerstore.controller;

import br.com.sunflowerstore.model.ItemSell;
import br.com.sunflowerstore.model.Sell;
import br.com.sunflowerstore.service.ProductService;
import br.com.sunflowerstore.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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


    @RequestMapping(value = "demo1/{product}", method = RequestMethod.GET, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> responseEntity(@PathVariable("product") Long product) {
        try {
            return new ResponseEntity<String>(String.valueOf(productService.get(product).getSellingPrice()) ,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("new2")
    public ModelAndView newSell2(Model model, @ModelAttribute("sell2") Sell sell) {
        //sell = new Sell();
        if(sell.getCode()==0L) {
            sell = sellService.save(sell);
            Long code = sell.getCode();
            //System.out.println(code);
        }

        ItemSell item = new ItemSell();
        item.setSell(sell);

        List<ItemSell> itemSells = new ArrayList<ItemSell>();

        ModelAndView mv = new ModelAndView("sell/new2");
        mv.addObject("produtos", productService.listInStock());
        mv.addObject("items",itemSells);
        mv.addObject("item",item);

        return mv;
    }


    @RequestMapping(value = "add2",method = RequestMethod.POST)
    public String add2(@ModelAttribute("item") ItemSell item) {
        /*System.out.println(item.getSell().getCode());
        System.out.println(item.getQtd());*/
        sellService.add(item.getSell(),item);
        System.out.println("id no add =" + item.getId());



        return "redirect:/sell/new22/" + item.getSell().getCode();
    }

    @RequestMapping(value = "new22/{code}")
    public ModelAndView new2(@PathVariable Long code, Model model) {

        ItemSell item = new ItemSell();
        item.setSell(sellService.getOne(code));
       // System.out.println("id no new2+code =" + sellService.getOne(code).getItems().get(0).getId());


        model.addAttribute("sell",sellService.getOne(code));
        model.addAttribute("items",sellService.getOne(code).getItems());
        model.addAttribute("item",item);
        model.addAttribute("produtos", productService.listInStock());


        return new ModelAndView("sell/new2");

    }

    @RequestMapping(value = "deleteItem/{code}/{id}",method = RequestMethod.GET)
    public String deleteItem(@PathVariable Long id, @PathVariable Long code, Model model) {
        sellService.deleteItem(sellService.getOne(code),id);
        return "redirect:/sell/new22/" + sellService.getOne(code).getCode();
    }

    @RequestMapping(value = "changeValue", method = RequestMethod.POST)
    @ResponseBody
    public BigDecimal changeValue(ItemSell itemSell) {
        return itemSell.getUnitValue();
    }



}
