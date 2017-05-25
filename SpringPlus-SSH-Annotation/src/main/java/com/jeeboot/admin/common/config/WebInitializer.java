package com.jeeboot.admin.common.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**     
 * WebApplicationInitializer是Spring用来提供配置Servlet3.0+的接口，从而实现了替代web.xml的配置。
 * 实现此接口将会自动被SpringServletContainerInitializer(用来启动Servlet3.0容器)获取到。 
 */
public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class);
        context.setServletContext(servletContext); // 新建WebApplicationContext,注册配置类，并将其和当前ServletContext关联

        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context)); // 注册Spring MVC的DispatcherServlet
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }

}
