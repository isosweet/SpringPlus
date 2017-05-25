package com.jeeboot.admin.common.config;

import java.io.IOException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import com.alibaba.druid.pool.DruidDataSource;

/**   
 * @Title: 
 * @Description: 
 * @author mayi
 *
 */
@Configuration
@PropertySource("classpath:application.properties") 
public class DataSourceConfig {
	
	@Value("${jdbc.driver}")
	private String driver;
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.username}")
	private String username;
	
	@Value("${jdbc.password}")
	private  String password;
	
	@Value("${hibernate.dialect}")
	private String dialect;
	
	/** 初始化连接大小 */
	@Value("${druid.initialSize}")
	private  int initialSize;
	
	/** 连接池最小空闲  */
	@Value("${druid.minIdle}")
	private  int minIdle;

	/** 连接池最大使用连接数量 */
	@Value("${druid.maxActive}")
	private  int maxActive;
	
	/** 获取连接最大等待时间 */
	@Value("${druid.maxWait}")
	private  int maxWait;
	
	/** druid 数据源 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	@Bean(destroyMethod = "close")
	public DruidDataSource dataSource(){
		
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource = new DruidDataSource();
		
//		druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		druidDataSource.setUrl(this.url);
		druidDataSource.setUsername(this.username);
		druidDataSource.setPassword(this.password);
		druidDataSource.setInitialSize(this.initialSize);
		druidDataSource.setMinIdle(this.minIdle);
		druidDataSource.setMaxActive(this.maxActive);
		druidDataSource.setMaxWait(this.maxWait);

		druidDataSource.setPoolPreparedStatements(false);
		return druidDataSource;
	}
	
	@Bean
    public SessionFactory sessionFactory() {
        try {
            LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
            lsfb.setDataSource(dataSource());
            //扫描实体类
            lsfb.setPackagesToScan("com.jeeboot.admin.entity");
            Properties props = new Properties();
            //设置方言
            props.setProperty("hibernate.dialect", dialect);
            lsfb.setHibernateProperties(props);
            lsfb.afterPropertiesSet();
            SessionFactory object = lsfb.getObject();
            return object;
        } catch (IOException e) {
            return null;
        }
    } 

	@Bean
	public HibernateTransactionManager transactionManager(){
		return new HibernateTransactionManager(this.sessionFactory());
	}
		
    // 若使用@Value注入，则要配置一个 PropertySourcesPlaceholderConfigurer 的Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigure(){
        return new PropertySourcesPlaceholderConfigurer();
    }

}
