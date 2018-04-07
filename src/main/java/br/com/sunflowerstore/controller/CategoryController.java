package br.com.sunflowerstore.controller;

import br.com.sunflowerstore.enums.Category;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by VictorJr on 31/03/2017.
 */

@Controller
@RequestMapping("/category/")
public class CategoryController {

    @RequestMapping("new")
    public String newCategory(Category category) {
        return "category/new";
    }
}
