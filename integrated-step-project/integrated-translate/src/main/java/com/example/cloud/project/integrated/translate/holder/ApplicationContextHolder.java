package com.example.cloud.project.integrated.translate.holder;

/**
 * @author gys
 * @version 1.0
 * @date 2023/9/28 14:19
 */

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取spring容器中所有bean的工具类，（比如接口为UserService，生成的bean为userService，默认小写）
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //将容器中的bean赋值给context,为了static赋值
        context = applicationContext;
    }

    /**
     * 根据指定的名字获取bean对象
     * @param name bean名称
     * @return 返回类型
     */
    public static Object getBean(String name) {
        if(context == null){
            return null;
        }
        return context.getBean(name);
    }

    public static <T> T getBean(Class<T> tClass) {
        if(context == null){
            return null;
        }
        return context.getBean(tClass);
    }
}

