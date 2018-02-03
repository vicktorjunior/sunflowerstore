package br.com.sunflowerstore.service.exception;

/**
 * Created by VictorJr on 02/05/2017.
 */
public class GenericException extends RuntimeException {
    public static final long serialVersionUID = 1L;
    
    String field;

    public GenericException(String message) {
        super(message);
    }

    public GenericException(String field, String message) {
    	super(message);
    	this.field = field;
    }

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
}
