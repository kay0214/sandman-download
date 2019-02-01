/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.config.thymeleaf;

import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.expression.IExpressionObjectFactory;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author sunpeikai
 * @version FileSizeExpressionFactory, v0.1 2018/12/7 15:21
 */
public class FilmNameExpressionFactory implements IExpressionObjectFactory {

    public static final String STRING_UTILS_EXPRESSION_OBJECT_NAME = "filmName";

    public static final FilmNameFormatUtils filmNameFormatUtils = new FilmNameFormatUtils();

    public static final Set<String> ALL_EXPRESSION_OBJECT_NAMES;

    static {
        final Set<String> allExpressionObjectNames = new LinkedHashSet<String>();
        allExpressionObjectNames.add(STRING_UTILS_EXPRESSION_OBJECT_NAME);
        ALL_EXPRESSION_OBJECT_NAMES = Collections.unmodifiableSet(allExpressionObjectNames);
    }

    public FilmNameExpressionFactory(){
        super();
    }

    @Override
    public Set<String> getAllExpressionObjectNames() {
        return ALL_EXPRESSION_OBJECT_NAMES;
    }

    @Override
    public Object buildObject(IExpressionContext context, String expressionObjectName) {
        return STRING_UTILS_EXPRESSION_OBJECT_NAME.equals(expressionObjectName) ? filmNameFormatUtils : null;
    }

    @Override
    public boolean isCacheable(String expressionObjectName) {
        return expressionObjectName != null && "filmName".equals(expressionObjectName);
    }
}
