/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.config.thymeleaf;

import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.expression.IExpressionObjectFactory;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author sunpeikai
 * @version FileSizeExpressionFactory, v0.1 2018/12/7 15:21
 */
public class FileSizeExpressionFactory implements IExpressionObjectFactory {

    public static final String STRING_UTILS_EXPRESSION_OBJECT_NAME = "size";

    public static final FileSizeFormatUtils fileSizeFormatUtils = new FileSizeFormatUtils();

    public static final Set<String> ALL_EXPRESSION_OBJECT_NAMES;

    static {
        final Set<String> allExpressionObjectNames = new LinkedHashSet<String>();
        allExpressionObjectNames.add(STRING_UTILS_EXPRESSION_OBJECT_NAME);
        ALL_EXPRESSION_OBJECT_NAMES = Collections.unmodifiableSet(allExpressionObjectNames);
    }

    public FileSizeExpressionFactory(){
        super();
    }

    @Override
    public Set<String> getAllExpressionObjectNames() {
        return ALL_EXPRESSION_OBJECT_NAMES;
    }

    @Override
    public Object buildObject(IExpressionContext context, String expressionObjectName) {
        return STRING_UTILS_EXPRESSION_OBJECT_NAME.equals(expressionObjectName) ? fileSizeFormatUtils : null;
    }

    @Override
    public boolean isCacheable(String expressionObjectName) {
        return expressionObjectName != null && "size".equals(expressionObjectName);
    }
}
