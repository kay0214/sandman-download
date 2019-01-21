/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.config.thymeleaf;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;
import org.thymeleaf.standard.StandardDialect;

/**
 * @author sunpeikai
 * @version FileSizeDialect, v0.1 2018/12/7 15:19
 */
public class FileSizeDialect extends AbstractDialect implements IExpressionObjectDialect {
    public FileSizeDialect() {
        super("size");
    }

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        return new FileSizeExpressionFactory();
    }
}
