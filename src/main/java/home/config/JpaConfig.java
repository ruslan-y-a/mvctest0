package home.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ImportResource({ "/WEB-INF/applicationContext.xml"})
@EnableJpaRepositories(basePackages = {"home"})
@EnableTransactionManagement(proxyTargetClass = true)
public class JpaConfig {
	
	    Properties additionalProperties() {
	    Properties properties = new Properties();
	    properties.setProperty("hibernate.hbm2ddl.auto", "update");
		  properties.setProperty("hibernate.show_sql", "true");
		    properties.setProperty("hibernate.format_sql", "true");
	    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
	       
	    return properties;
	}     
///////////////////////////////////////////////////////////////////	
	@Bean
	public DataSource dataSource(){
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("org.postgresql.Driver");
	    dataSource.setUrl("jdbc:postgresql://localhost:5432/phones");
	    dataSource.setUsername( "root" );
	   dataSource.setPassword( "root" );
	    return dataSource;
	}
	 @Bean
	    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		    LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
	        factoryBean.setPersistenceUnitName("phones");
		    factoryBean.setDataSource(dataSource());
	        factoryBean.setJpaProperties(additionalProperties());
	        factoryBean.setPackagesToScan(new String[] { "home.entities" }); //.entities
	        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	        factoryBean.setJpaVendorAdapter(vendorAdapter);
	          
	        return factoryBean;
	    }
	 @Bean(name="transactionManager")
	    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
	        JpaTransactionManager transactionManager = new JpaTransactionManager();
	        transactionManager.setEntityManagerFactory(entityManagerFactory);       
	        return transactionManager;
	    }
}
