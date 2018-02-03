package br.com.sunflowerstore.controller;

import br.com.sunflowerstore.enums.Category;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by VictorJr on 31/03/2017.
 */

@Controller
public class CategoryController {

    @RequestMapping("categorias/nova")
    public String novo(Category category) {
        return "category/CadastroCategoria";
    }
}
