package br.com.sunflowerstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

/**
 * Created by rodrigo on 3/16/17.
 */
@Component
public class Messages {

	@Autowired
	private MessageSource messageSource;

	private MessageSourceAccessor accessor;

	/*@PostConstruct
	private void init() {
		accessor = new MessageSourceAccessor(messageSource, Locale.ENGLISH);
	}*/

	public String get(String code) {
		return accessor.getMessage(code);
	}

}