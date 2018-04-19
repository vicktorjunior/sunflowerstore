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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
            System.out.println(code);
        }

        ItemSell item = new ItemSell();
        item.setSell(sell);

        List<ItemSell> itemSells = new ArrayList<ItemSell>();
        itemSells.add(new ItemSell(3,new BigDecimal(10),productService.get(1L),sell));

        ModelAndView mv = new ModelAndView("sell/new2");
        mv.addObject("produtos", productService.listInStock());
        mv.addObject("items",itemSells);
        mv.addObject("item",item);

        return mv;
    }


    @RequestMapping(value = "add2",method = RequestMethod.POST)
    public ModelAndView add2(@ModelAttribute("item") ItemSell item) {
        System.out.println(item.getSell().getCode());
        System.out.println(item.getQtd());
        sellService.add(item.getSell(),item);

        ModelAndView mv = new ModelAndView("redirect:/sell/new2");
        mv.addObject("sell2",item.getSell());

        return mv;
    }



	@RequestMapping("new")
    public ModelAndView newSell(@ModelAttribute("itemSell") ItemSell itemSell, Model model,
                                RedirectAttributes redirectAttributes, @ModelAttribute("sell") Sell sell) {
        /*List<ItemSell> items = new ArrayList<>();
        items.add(new ItemSell());*/

//        if(redirectAttributes.getFlashAttributes().get("sell") != null) {
//            System.out.println("if");
//            sell = (Sell) redirectAttributes.getFlashAttributes().get("sell");
//        } else {

        if (sellService.getOne(1000002L) != null) {
            sell = sellService.getOne(1L);
        } else {
            //System.out.println(model.asMap().get("sell").toString());
            System.out.println("else");
            sell = new Sell();
            sell = sellService.save(sell);
         }
        List<ItemSell> itemSells = new ArrayList<ItemSell>();
        itemSells.add(new ItemSell(1, new BigDecimal("0") , productService.get(1L),sell));

        sell.setItems(itemSells);
        ModelAndView mv = new ModelAndView("/sell/new");

        mv.addObject("produtos", productService.listInStock());
        mv.addObject("itemSell",new ItemSell());
        mv.addObject("sell", sell);
        mv.addObject("items",sell.getItems());
        return mv;
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public ModelAndView add(@Valid @ModelAttribute("itemSell") ItemSell itemSell, BindingResult result,
                   RedirectAttributes redirectAttributes, Model model) {

        Sell sell = sellService.getOne(itemSell.getSell().getCode());
        itemSell.setProduct(productService.get(itemSell.getProduct().getCode()));
        if (sell.getItems() == null)
            sell.setItems(new ArrayList<>());
        sell.getItems().add(itemSell);

        sellService.add(sell,itemSell);

        ModelAndView mv =  new ModelAndView("redirect:/sell/new");
        mv.addObject("sell", sell);
        mv.addObject("items", sell.getItems());
        return mv;

    }




    /*@PostMapping("save")
    public ModelAndView save(@ModelAttribute("question") Question question, BindingResult bindingResult,
                             RedirectAttributes redirectAttr, @AuthenticationPrincipal UserImpl activeUser, Errors errors, Model model) {
        if (bindingResult.hasErrors()) {
            redirectAttr.addFlashAttribute(errors);
            return new ModelAndView("/question/list");
        }
        if (question.getAlternatives().size() == 0)
            question.setAlternatives(new LinkedList<>());

        if (question.getCorrect() != null && !question.getCorrect().isEmpty()) {
            question.getAlternatives().get(Integer.parseInt(question.getCorrect())).setCorrect(true);
        }
        questionService.save(question);
        return new ModelAndView("redirect:/question/list");
    }*/





    @RequestMapping(value = "changeValue", method = RequestMethod.POST)
    @ResponseBody
    public BigDecimal changeValue(ItemSell itemSell) {
        return itemSell.getUnitValue();
    }



}
