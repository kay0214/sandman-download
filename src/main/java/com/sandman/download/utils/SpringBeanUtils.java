/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.utils;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author sunpeikai
 * @version SpringBeanUtils, v0.1 2019/1/18 17:38
 */
public class SpringBeanUtils {
    private static ApplicationContext context = new ClassPathXmlApplicationContext();
    private static ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext)context;
    private static BeanDefinitionRegistry beanDefinitionRegistry = (DefaultListableBeanFactory)applicationContext.getBeanFactory();
    public static void registerBean(String beanId,String className){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(className);
        BeanDefinition beanDefinition=beanDefinitionBuilder.getBeanDefinition();
        beanDefinitionRegistry.registerBeanDefinition(beanId,beanDefinition);
    }
    public static void unregisterBean(String beanId){
        beanDefinitionRegistry.removeBeanDefinition(beanId);
    }
    public static <T> T getBean(String name) {
        return (T) context.getBean(name);
    }
}
