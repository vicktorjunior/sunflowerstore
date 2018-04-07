package br.com.sunflowerstore.controller;

import br.com.sunflowerstore.model.Supplier;
import br.com.sunflowerstore.service.SupplierService;
import br.com.sunflowerstore.service.exception.SupplierAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by VictorJr on 25/04/2017.
 */
@Controller
@RequestMapping("/supplier/")
public class SupplierController {

	@Autowired
	private SupplierService supplierService;

	@RequestMapping("new")
	public ModelAndView newSupplier(Supplier supplier) {
		return new ModelAndView("supplier/new");
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView saveSupplier(@Valid Supplier supplier, BindingResult result,
									 RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return newSupplier(supplier);
		}
		try {
			supplierService.save(supplier);
		} catch (SupplierAlreadyExistsException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return newSupplier(supplier);
		}
		redirectAttributes.addFlashAttribute("mensagem", "Supplier Salvo com Sucesso"); // TODO: MSG003
		return new ModelAndView("redirect:/supplier/new");
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> save(@RequestBody @Valid Supplier supplier, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		supplier = supplierService.save(supplier);
		return ResponseEntity.ok(supplier);
	}
}
