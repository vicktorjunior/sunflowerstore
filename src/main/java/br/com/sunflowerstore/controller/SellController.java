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
import java.time.LocalDate;
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



    @RequestMapping("new")
    public ModelAndView newSell(Model model, @ModelAttribute("sell") Sell sell) {
        //sell = new Sell();
        if(sell.getCode()==0L) {
            sell = sellService.save(sell);
            Long code = sell.getCode();
            //System.out.println(code);
        }

        ItemSell item = new ItemSell();
        item.setSell(sell);

        List<ItemSell> itemSells = new ArrayList<ItemSell>();

        ModelAndView mv = new ModelAndView("sell/new");
        model.addAttribute("sell",sell);
        mv.addObject("produtos", productService.listInStock());
        mv.addObject("items",itemSells);
        mv.addObject("item",item);

        return mv;
    }

    @RequestMapping(value = "new/{code}")
    public ModelAndView new2(@PathVariable Long code, Model model) {

        ItemSell item = new ItemSell();
        item.setSell(sellService.getOne(code));

        model.addAttribute("sell",sellService.getOne(code));
        model.addAttribute("items",sellService.getOne(code).getItems());
        model.addAttribute("item",item);
        model.addAttribute("produtos", productService.listInStock());

        return new ModelAndView("sell/new");

    }



    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String add2(@ModelAttribute("item") ItemSell item) {
        /*System.out.println(item.getSell().getCode());
        System.out.println(item.getQtd());*/
        sellService.add(item.getSell(),item);
        Integer quantity = item.getQtd();
        Long id = item.getProduct().getCode();

        productService.removeQtd(id,quantity);

        return "redirect:/sell/new/" + item.getSell().getCode();
    }

    @RequestMapping(value = "total/{sell}/{total}", method = RequestMethod.GET, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> total(@PathVariable("total") BigDecimal total, @PathVariable("sell") Long sell) {
        try {
            sellService.getOne(sell).setTotalSell(total);
            return new ResponseEntity<String>("/sell/new" ,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }



    @RequestMapping(value = "deleteItem/{code}/{id}",method = RequestMethod.GET)
    public String deleteItem(@PathVariable Long id, @PathVariable Long code, Model model) {
        sellService.deleteItem(sellService.getOne(code),id);
        return "redirect:/sell/new/" + sellService.getOne(code).getCode();
    }

    /*@RequestMapping(value = "changeValue", method = RequestMethod.POST)
    @ResponseBody
    public BigDecimal changeValue(ItemSell itemSell) {
        return itemSell.getUnitValue();
    }*/


    @GetMapping("list")
    public ModelAndView list(Model model) {
        LocalDate localDate = LocalDate.now();

        model.addAttribute("sells", sellService.getByDate(localDate));
        //model.addAttribute("action", "list");
        //System.out.println("passou reto");
        return new ModelAndView("sell/list");
    }



    @RequestMapping("cancel/{code}")
    public String cancel(@PathVariable Long code, Model model) {
        Sell s = sellService.getOne(code);
        sellService.cancelSell(s);

        return "redirect:/";
    }



}
