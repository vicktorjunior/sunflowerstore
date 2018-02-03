package br.com.sunflowerstore.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.sunflowerstore.service.exception.GenericException;

/**
 * Created by VictorJr on 22/05/2017.
 */
@ControllerAdvice
public class ControllerAdviceExceptionHandler {

/*	@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }*/

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<String> handleAllExceptions(GenericException e) {
    	return ResponseEntity.badRequest().body(e.getMessage());
    	/*if (e.getField() != null) {
    		result.rejectValue(e.getField(), e.getMessage(), e.getMessage());
    	} else {
    		redirectAttributes.addFlashAttribute("mensagem", e.getMessage());
    	}*/
    }

}
