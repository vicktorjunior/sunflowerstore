package br.com.sunflowerstore.service;

import br.com.sunflowerstore.model.Product;
import br.com.sunflowerstore.repository.ProductRepository;
import br.com.sunflowerstore.service.exception.GenericException;
import br.com.sunflowerstore.service.exception.ProductAlreadyExistsException;
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

	public ProductService(ProductRepository products, JavaMailSender javaMailSender) {
		this.productRepository = products;
		this.javaMailSender = javaMailSender;

	}

    @Transactional
    public Product save(Product product) {
		String nameProduct = product.getName();
    	/*product.setNickname(validaApelido(product.getNickname()));
    	product.setCategory(product.getCategory());
    	product.setBuyingDate(product.getBuyingDate());
    	product.setDescription(product.getDescription());
    	product.setOrigin(product.getOrigin());
    	product.setBuyingPrice(product.getBuyingPrice());
    	product.setSellingPrice(product.getSellingPrice());
    	product.setPercentage(product.getPercentage());*/
    	if (productRepository.findByNameIgnoreCase(nameProduct).isEmpty()) {
			return productRepository.save(product);
		} else {
			throw new ProductAlreadyExistsException("Product já existe no sistema!");
		}

    }


    //TODO: RNG002
    private String validaApelido(String nickname) {
    	if (StringUtils.hasText(nickname)) {
    		nickname = nickname.trim().toUpperCase();
    		if (!Pattern.matches("([a-zA-Z]{2}\\d{4})?", nickname)) {
    			throw new GenericException("apelido", "Apelido deve seguir o padrão XX9999");
    		}
    		return nickname;
    	} 
    	return null;
    }

	public List<Product> listAll() {
		return productRepository.findAll();
	}

	public List<Product> listInStock() {
		return productRepository.findByQtdGreaterThan(0);
	}

	public Product get(Long id) {
		return productRepository.getOne(id);
	}

	public void sendMessage(Product product) {
		SimpleMailMessage mail = new SimpleMailMessage();

		if (product != null ) {
			if (product.getName() != null) {
					mail.setTo("sellstockapp@gmail.com");
					mail.setFrom("sellstockapp@gmail.com");
					mail.setSubject("Um newProduct produto foi cadastrado com sucesso!");
					mail.setText(
							"Prezado(a) Administrador(a). Seu produto: '"
									+ product.getName() + "' foi cadastrado com sucesso!\n");
					javaMailSender.send(mail);


			}
		}
	}

	public void delete(Long id){
		productRepository.delete(id);
	}
}
