package br.com.sunflowerstore.controller;

import br.com.sunflowerstore.enums.Category;
import br.com.sunflowerstore.enums.Origin;
import br.com.sunflowerstore.model.Product;
import br.com.sunflowerstore.service.ProductService;
import br.com.sunflowerstore.service.SupplierService;
import br.com.sunflowerstore.service.exception.ProductAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by VictorJr on 23/03/2017.
 */
@Controller
@RequestMapping("/products/")
public class ProductController {

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private ProductService productService;

	@RequestMapping("new")
	public ModelAndView newProduct(Product product) {
		ModelAndView mv = new ModelAndView("product/new");
		mv.addObject("categorias", Category.values());
		mv.addObject("fornecedores", supplierService.listAll());
		mv.addObject("origens", Origin.values());
		return mv;
	}

	@RequestMapping(value = "new", method = RequestMethod.POST)
	public ModelAndView saveProduct(@ModelAttribute("product")@Valid Product product, BindingResult result,
									RedirectAttributes redirectAttributes, Model model) {
		if (result.hasErrors()) {
			return newProduct(product);
		}
		try {
			productService.save(product);
		} catch(ProductAlreadyExistsException e) {
			result.rejectValue("name", e.getMessage(), e.getMessage());
			return newProduct(product);
		}
		//productService.sendMessage(product);
		redirectAttributes.addFlashAttribute("mensagem", "Product Salvo com Sucesso"); // TODO: MSG003
		return new ModelAndView("redirect:/products/new");
	}

	/*@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView("product/PesquisaProduto");
		mv.addObject("categorias", Category.values());
		mv.addObject("fornecedores", fornecedores.findAll());
		mv.addObject("origens", Origin.values());
		mv.addObject("produtos", produtos.findAll());
		return mv;
	}*/

	@GetMapping("list")
	public ModelAndView list(Model model) {
		model.addAttribute("products", productService.listAll());
		model.addAttribute("action", "list");
		//System.out.println("passou reto");
		return new ModelAndView("product/list");
	}

	@GetMapping("edit/{id}")
	public ModelAndView edit(Model model, @PathVariable Long id) {
		ModelAndView mv = new ModelAndView("product/new");
		model.addAttribute("product", productService.get(id));
		mv.addObject("categorias", Category.values());
		mv.addObject("fornecedores", supplierService.listAll());
		mv.addObject("origens", Origin.values());
		model.addAttribute("action", "record");
		return mv;
	}

	@GetMapping("delete/{id}")
	public String delete(Model model, @PathVariable Long id) {
		productService.delete(id);
		return "redirect:/products/list";
	}

	@GetMapping("view/{id}")
	public ModelAndView view(Model model, @PathVariable Long id) {
		model.addAttribute("product", productService.get(id));
		model.addAttribute("action", "view");
		return new ModelAndView("product/view");
	}




}
