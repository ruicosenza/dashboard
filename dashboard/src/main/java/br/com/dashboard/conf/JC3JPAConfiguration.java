package br.com.dashboard.conf;

import java.util.HashMap;

import javax.persistence.PersistenceUnit;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({ "file:/home/monitor/persistence-multiple-db.properties" })
@EnableJpaRepositories(basePackages = "br.com.dashboard.daos", 
entityManagerFactoryRef = "jc3EntityManager", 
transactionManagerRef = "jc3TransactionManager")
@PersistenceUnit(name="jc3PU")
public class JC3JPAConfiguration {
	
	@Autowired
	private Environment env;
	
	public JC3JPAConfiguration() {
		super();
	}

	@Bean(name="jc3")
    public LocalContainerEntityManagerFactoryBean jc3EntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(jc3DataSource());
		em.setPackagesToScan(new String[] { "br.com.dashboard.models.jc3" });

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setShowSql(true);
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("spring.jpa.hibernate.ddl-auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.put("spring.jpa.dialect", env.getProperty("spring.jpa.hibernate.dialect"));
		properties.put("spring.jpa.generate-ddl", "true");
		properties.put("spring.jpa.hibernate.show-sql", "true");
		em.setJpaPropertyMap(properties);

		return em;
    }
 
    @Bean
    public DataSource jc3DataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("driver-class-name"));
        
//        System.out.println("#######################################");
//        System.out.println(env.getProperty("url-jc3"));
//        System.out.println(env.getProperty("driver-class-name"));
//        System.out.println("#######################################");
        
        dataSource.setUrl(env.getProperty("url-jc3"));
        dataSource.setUsername(env.getProperty("user-name"));
        dataSource.setPassword(env.getProperty("user-password"));
 
        return dataSource;
    }
 
    @Bean
    public PlatformTransactionManager jc3TransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(jc3EntityManager().getObject());
        return transactionManager;
    }
}