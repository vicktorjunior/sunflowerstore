package br.com.sunflowerstore.service;

import br.com.sunflowerstore.model.Product;
import br.com.sunflowerstore.repository.ProductRepository;
import br.com.sunflowerstore.service.exception.GenericException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by VictorJr on 10/04/2017.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;
	private final JavaMailSender javaMailSender;

	public ProductService(ProductRepository produtos, JavaMailSender javaMailSender) {
		this.productRepository = produtos;
		this.javaMailSender = javaMailSender;

	}

    @Transactional
    public Product salvar(Product product) {
    	/*product.setApelido(validaApelido(product.getApelido()));
    	product.setCategory(product.getCategory());
    	product.setDataCompra(product.getDataCompra());
    	product.setDescricao(product.getDescricao());
    	product.setOrigin(product.getOrigin());
    	product.setPrecoCompra(product.getPrecoCompra());
    	product.setPrecoVenda(product.getPrecoVenda());
    	product.setPercentual(product.getPercentual());*/
        return productRepository.save(product);
    }


    //TODO: RNG002
    private String validaApelido(String apelido) {
    	if (StringUtils.hasText(apelido)) {
    		apelido = apelido.trim().toUpperCase();
    		if (!Pattern.matches("([a-zA-Z]{2}\\d{4})?", apelido)) {
    			throw new GenericException("apelido", "Apelido deve seguir o padrão XX9999");
    		}
    		return apelido;    		
    	} 
    	return null;
    }

	public List<Product> listAll() {
		return productRepository.findAll();
	}

	public Product get(Long id) {
		return productRepository.getOne(id);
	}

	public void sendMessage(Product product) {
		SimpleMailMessage mail = new SimpleMailMessage();

		if (product != null ) {
			if (product.getNome() != null) {
					mail.setTo("sellstockapp@gmail.com");
					mail.setFrom("sellstockapp@gmail.com");
					mail.setSubject("Um novo produto foi cadastrado com sucesso!");
					mail.setText(
							"Prezado(a) Administrador(a). Seu produto: '"
									+ product.getNome() + "' foi cadastrado com sucesso!\n");
					javaMailSender.send(mail);


			}
		}
	}
}
