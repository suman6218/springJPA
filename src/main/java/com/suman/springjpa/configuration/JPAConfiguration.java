package com.suman.springjpa.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.OpenJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class JPAConfiguration {

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
		ds.setUrl(env.getRequiredProperty("jdbc.url"));
		ds.setUsername(env.getRequiredProperty("jdbc.username"));
		ds.setPassword(env.getRequiredProperty("jdbc.password"));

		return ds;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		//em.setPersistenceUnitName("localDB");
		em.setPackagesToScan(new String[] { "com.suman.springjpa.entity" });
		em.setJpaVendorAdapter(jpaVendorAdapter());
		em.setJpaProperties(jpaProperties());
		// em.setJpaPropertyMap(jpaProperties());
		return em;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		OpenJpaVendorAdapter jpaVendorAdapter = new OpenJpaVendorAdapter();

		jpaVendorAdapter.setDatabase(Database.valueOf(env.getRequiredProperty("jpa.database")));
		jpaVendorAdapter.setShowSql(Boolean.parseBoolean(env.getRequiredProperty("jpa.show_sql")));
		jpaVendorAdapter.setGenerateDdl(Boolean.parseBoolean(env.getRequiredProperty("jpa.generateDdl")));
		return jpaVendorAdapter;
	}

	@Bean
	public PlatformTransactionManager  transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
		return jpaTransactionManager;
	}

	
	private Properties jpaProperties() {
		Properties properties = new Properties();
		properties.put("openjpa.RuntimeUnenhancedClasses", "supported");
		properties.put("openjpa.Log", "DefaultLevel=WARN");
		// properties.put("openjpa.DynamicEnhancementAgent", true);		
		return properties;
	}
	
}
