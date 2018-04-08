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

	@RequestMapping("new")
    public ModelAndView newSell(ItemSell itemSell, Model model) {
        /*List<ItemSell> items = new ArrayList<>();
        items.add(new ItemSell());*/
        Sell sell;
        if(model.asMap().get("items") != null) {
            System.out.println("if");
           sell = (Sell) model.asMap().get("sell");
        } else {
            System.out.println("else");
            sell = new Sell();
        }
        List<ItemSell> itemSells = new ArrayList<ItemSell>();

        itemSells.add(new ItemSell(1, new BigDecimal("0") , productService.get(1L),sell));

        sell.setItems(itemSells);




        ModelAndView mv = new ModelAndView("sell/new");


        mv.addObject("produtos", productService.listInStock());
        mv.addObject("itemSell",new ItemSell());
        mv.addObject("sell", sell);
        mv.addObject("items",sell.getItems());
        return mv;
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView add(@Valid ItemSell itemSell, BindingResult result,
                   RedirectAttributes redirectAttributes, Model model, @ModelAttribute("sell") Sell sell) {
        if (result.hasErrors()) {
            System.out.println("errou!");
          //  return newProduct(itemSell);
        }

        try {
            itemSell.setProduct(productService.get(itemSell.getProduct().getCode()));
            sell.getItems().add(itemSell);

            sellService.add(sell,itemSell);
        } catch (Exception e) {
            e.printStackTrace();
        }


        redirectAttributes.addAttribute("sell", sell);
        redirectAttributes.addAttribute("items", sell.getItems());
        ModelAndView mv =  new ModelAndView("redirect:/sell/new");

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
