package br.com.sunflowerstore.controller;

import br.com.sunflowerstore.enums.Category;
import br.com.sunflowerstore.enums.Origin;
import br.com.sunflowerstore.model.Product;
import br.com.sunflowerstore.repository.ProductRepository;
import br.com.sunflowerstore.repository.SupplierRepository;
import br.com.sunflowerstore.service.ProductService;
import br.com.sunflowerstore.service.exception.ProdutoJaCadastradoException;
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
	private SupplierRepository supplierRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepository;

	@RequestMapping("new")
	public ModelAndView novo(Product product) {
		ModelAndView mv = new ModelAndView("product/new");
		mv.addObject("categorias", Category.values());
		mv.addObject("fornecedores", supplierRepository.findAll());
		mv.addObject("origens", Origin.values());
		return mv;
	}

	@RequestMapping(value = "new", method = RequestMethod.POST)
	public ModelAndView cadastrar(@ModelAttribute("product")@Valid Product product, BindingResult result,
								  RedirectAttributes redirectAttributes, Model model) {
		if (result.hasErrors()) {
			System.out.println("errou!");
			return novo(product);
		}
		try {
			productService.salvar(product);
		} catch(ProdutoJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(product);
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
		mv.addObject("fornecedores", supplierRepository.findAll());
		mv.addObject("origens", Origin.values());
		model.addAttribute("action", "record");
		return mv;
	}

	@GetMapping("delete/{id}")
	public String delete(Model model, @PathVariable Long id) {
		productRepository.delete(id);
		return "redirect:/products/list";
	}

	@GetMapping("view/{id}")
	public ModelAndView view(Model model, @PathVariable Long id) {
		model.addAttribute("product", productService.get(id));
		model.addAttribute("action", "view");
		return new ModelAndView("product/view");
	}




}
