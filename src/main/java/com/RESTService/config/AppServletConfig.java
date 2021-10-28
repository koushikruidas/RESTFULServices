package com.RESTService.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "com.RESTService")
@PropertySource("classpath:database_property.properties")
public class AppServletConfig {
	
	// Set up a variable to hold the property values
	@Autowired
	private Environment env;
	
	// Set up a logger for diagonostic
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// Define the internal view resolver
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	// Defining a security dataSource
	// Also, here using property from the resources/*.properties file  
	@Bean
	public ComboPooledDataSource myDataSource() {
		logger.info(">>> inside: "+env.getClass().getName());
		ComboPooledDataSource ds = new ComboPooledDataSource();
		try {
			ds.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		logger.info(">>> jdbc.url=" +env.getProperty("jdbc.url"));
		ds.setJdbcUrl(env.getProperty("jdbc.url"));
		
		logger.info(">>> jdbc.user=" +env.getProperty("jdbc.user"));
		ds.setUser(env.getProperty("jdbc.user"));
		
		logger.info(">>> jdbc.password=" +env.getProperty("jdbc.password"));
		ds.setPassword(env.getProperty("jdbc.password"));
		
		logger.info(">>> jdbc.initialPoolSize=" +env.getProperty("connection.pool.initialPoolSize"));
		ds.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize")); // Here called the helper method
																				  // as this method takes int value as 
																				  // argument.
		logger.info(">>> jdbc.minPoolSize=" +env.getProperty("connection.pool.minPoolSize"));
		ds.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		
		logger.info(">>> jdbc.maxPoolSize=" +env.getProperty("connection.pool.maxPoolSize"));
		ds.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		
		logger.info(">>> jdbc.maxIdleTime=" +env.getProperty("connection.pool.maxIdleTime"));
		ds.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		
		return ds;
	}
	
	// Need a helper method to read environment property and convert it in INT
	private int getIntProperty(String propName) {
		String propVal = env.getProperty(propName);
		
		// Now convert it to int
		int intPropVal = Integer.parseInt(propVal);
		return intPropVal;
	}

	// Hibernate session factory bean

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(myDataSource());
		sessionFactory.setPackagesToScan("com.RESTService.entity");
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	// Defining the hibernate properties
	private final Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		hibernateProperties.setProperty("hibernate.show_sql", "true");

		return hibernateProperties;
	}

	// Set up hibernate transaction manager
	@Bean
	public PlatformTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}
}
