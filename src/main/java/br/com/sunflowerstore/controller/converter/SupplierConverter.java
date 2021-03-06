package br.com.sunflowerstore.controller.converter;

import br.com.sunflowerstore.model.Supplier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * Created by VictorJr on 10/04/2017.
 */
public class SupplierConverter implements Converter<String,Supplier> {

    @Override
    public Supplier convert(String code) {
        if(!StringUtils.isEmpty(code)) {
            Supplier supplier = new Supplier();
            supplier.setCode(Long.valueOf(code));
            return supplier;
        }
        return null;
    }
}
