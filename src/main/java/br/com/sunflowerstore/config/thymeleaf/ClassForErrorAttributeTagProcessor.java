package br.com.sunflowerstore.config.thymeleaf;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring4.util.FieldUtils;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * Created by VictorJr on 22/05/2017.
 */
public class ClassForErrorAttributeTagProcessor extends AbstractAttributeTagProcessor{

    private static final String ATTRIBUTE_NAME = "classforerror";
    public static final int PRECEDENCE = 1000;

    public ClassForErrorAttributeTagProcessor(String dialectPrefix) {
        super(TemplateMode.HTML, dialectPrefix, null, false, ATTRIBUTE_NAME, true, PRECEDENCE, true);
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue, IElementTagStructureHandler structureHandler) {
        boolean temErro = FieldUtils.hasErrors(context,attributeValue);

        if (temErro) {
            String existingClasses = tag.getAttributeValue("class");
            structureHandler.setAttribute("class", existingClasses + " has-error");
        }
    }
}
