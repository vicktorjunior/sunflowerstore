package br.com.sunflowerstore.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

/**
 * Created by VictorJr on 10/04/2017.
 */
@Component
public class BuyingPriceConverter implements Converter<String, BigDecimal> {

	@Override
	public BigDecimal convert(String precoString) {
		if (!StringUtils.isEmpty(precoString)) {

			precoString = precoString.replace(".", "");

			precoString = precoString.replace(",", ".");

			return new BigDecimal(precoString);

		}
		return new BigDecimal(precoString);
	}
}
