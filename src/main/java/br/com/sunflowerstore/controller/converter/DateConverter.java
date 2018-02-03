package br.com.sunflowerstore.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by VictorJr on 10/04/2017.
 */
@Component
public class DateConverter implements Converter<String, LocalDate> {

	@Override
	public LocalDate convert(String date) {
		if (!StringUtils.isEmpty(date)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			formatter.withLocale(new Locale("pt", "br"));
			LocalDate data = LocalDate.parse(date, formatter);
			return data;
		}
		return null;
	}
}
