package com.neo4j.Neo4j;

import com.neo4j.Neo4j.mysql.*;
import com.neo4j.Neo4j.neo4j.ArtistRepository;
import com.neo4j.Neo4j.neo4j.PersonRepository;
import com.neo4j.Neo4j.neo4j.SongRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@EnableJpaRepositories(basePackageClasses = {com.neo4j.Neo4j.mysql.ArtistRepositoryMysql.class, com.neo4j.Neo4j.mysql.SongRepositoryMysql.class,com.neo4j.Neo4j.mysql.PersonRepositoryMysql.class})
@EnableNeo4jRepositories(basePackageClasses = {SongRepository.class, ArtistRepository.class, PersonRepository.class})

@SpringBootApplication
public class Neo4jApplication {




	public static void main(String[] args) {
		SpringApplication.run(Neo4jApplication.class, args);
	}


}


/*Configuration
@ComponentScan({ "myproject" })
@EnableJpaRepositories(basePackages = "myproject.persistence.mysql")
@EnableAutoConfiguration
@EnableNeo4jRepositories(basePackages = "myproject.persistence.neo4j")
public class Application extends Neo4jConfiguration
{
	@Autowired
	LocalContainerEntityManagerFactoryBean entityManagerFactory;

	public Application()
	{
		setBasePackage("myproject.persistence.neo4j");
	}

	@Bean
	SpringRestGraphDatabase graphDatabaseService()
	{
		return new SpringRestGraphDatabase("http://localhost:7474/db/data");
	}

	@Bean(destroyMethod = "close")
	public DataSource dataSource()
	{
		DataSource dataSource = new DataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/dbname");
		dataSource.setUsername("mysqluser");
		dataSource.setPassword("mysqlpassword");
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory()
	{
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPackagesToScan("myproject.persistence.mysql");
		entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
		Map<String, String> jpaProperties = new HashMap<String, String>();
		jpaProperties.put("hibernate.connection.charSet", "UTF-8");
		jpaProperties.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.EJB3NamingStrategy");
		jpaProperties.put("hibernate.bytecode.provider", "javassist");
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		entityManagerFactory.setJpaPropertyMap(jpaProperties);
		entityManagerFactory.setPersistenceProvider(new HibernatePersistence());
		return entityManagerFactory;
	}

	@Override
	@Bean(name = "transactionManager")
	public PlatformTransactionManager neo4jTransactionManager() throws Exception
	{
		return new ChainedTransactionManager(new JpaTransactionManager(entityManagerFactory.getObject()),
				new JtaTransactionManagerFactoryBean(graphDatabaseService()).getObject());
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation()
	{
		return new PersistenceExceptionTranslationPostProcessor();
	}
}*/