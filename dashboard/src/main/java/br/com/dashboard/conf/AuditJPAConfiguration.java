package br.com.dashboard.conf;

import java.util.HashMap;

import javax.persistence.PersistenceUnit;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
entityManagerFactoryRef = "auditEntityManager", 
transactionManagerRef = "auditTransactionManager")
@PersistenceUnit(name = "auditPU")
public class AuditJPAConfiguration {

	@Autowired
	private Environment env;
	
	public AuditJPAConfiguration() {
		super();
	}

	@Bean(name="audit")
	@Primary
	public LocalContainerEntityManagerFactoryBean auditEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(auditDataSource());
		em.setPackagesToScan(new String[] { "br.com.dashboard.models.audit", "br.com.dashboard.models.olap" });

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
	@Primary
	public DataSource auditDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("driver-class-name"));
		
//		System.out.println("#######################################");
//		System.out.println(env.getProperty("url-audit"));
//		System.out.println(env.getProperty("driver-class-name"));
//		System.out.println("#######################################");
		
		dataSource.setUrl(env.getProperty("url-audit"));
		dataSource.setUsername(env.getProperty("user-name"));
		dataSource.setPassword(env.getProperty("user-password"));

		return dataSource;
	}

	@Bean
	@Primary
	public PlatformTransactionManager auditTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(auditEntityManager().getObject());
		return transactionManager;
	}
}