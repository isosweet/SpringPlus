package com.jeeboot.admin.common.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.jeeboot.admin.BlankInterface;
import com.jeeboot.admin.util.JsonMapper;

/**
 * 配置类
 * 
 * 
 * 
 * @Configuration ： 声明当前类是一个配置类
 * @EnableWebMvc ：开启WEB MVC的配置支持
 * @ComponentScan：自动扫描包下使用@Controller、@Service、@Component、@Repository、的类，并注册为Bean
 * 
 * 继承一个WebMvcConfigurerAdapter类，重写这个类的方法，完成常用配置
 */

@Configuration 
@EnableWebMvc
@ComponentScan(basePackageClasses=BlankInterface.class)
@EnableTransactionManagement
public class WebConfig extends WebMvcConfigurerAdapter{
	
	
	 /** 视图解析器 */
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/"); // 运行时的目录结构
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }
    
    /** 静态资源映射 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**") // 对外暴露的访问路径
                .addResourceLocations("classpath:/assets/"); // 文件放置 的目录
    }
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		
		/** 将StringHttpMessageConverter的默认编码设为UTF-8 避免出现乱码 */
		StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		converters.add(stringHttpMessageConverter);
		
		MappingJackson2HttpMessageConverter jackson = new MappingJackson2HttpMessageConverter();
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		MediaType MediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
		supportedMediaTypes.add(MediaType);
		jackson.setSupportedMediaTypes(supportedMediaTypes);
		jackson.setPrettyPrint(false);
		jackson.setObjectMapper(new JsonMapper());
		converters.add(jackson);
		
	}
}
