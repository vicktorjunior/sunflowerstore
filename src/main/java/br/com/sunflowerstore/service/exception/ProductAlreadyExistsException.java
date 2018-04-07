package br.com.sunflowerstore.service.exception;

/**
 * Created by VictorJr on 02/05/2017.
 */
public class ProductAlreadyExistsException extends RuntimeException {
    public static final long serialVersionUID = 1L;

    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
