package br.com.sunflowerstore.service.exception;

/**
 * Created by VictorJr on 02/05/2017.
 */
public class NomeFornecedorJaCadastradoException extends RuntimeException {
    public static final long serialVersionUID = 1L;

    public NomeFornecedorJaCadastradoException(String message) {
        super(message);
    }
}
