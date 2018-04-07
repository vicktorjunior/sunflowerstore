package br.com.sunflowerstore.service.exception;

/**
 * Created by VictorJr on 02/05/2017.
 */
public class SupplierAlreadyExistsException extends RuntimeException {
    public static final long serialVersionUID = 1L;

    public SupplierAlreadyExistsException(String message) {
        super(message);
    }
}
