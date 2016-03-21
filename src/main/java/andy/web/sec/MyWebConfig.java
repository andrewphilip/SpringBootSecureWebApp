package andy.web.sec;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class MyWebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	        registry.addViewController("/home").setViewName("home");
	        registry.addViewController("/").setViewName("home");
	        registry.addViewController("/hello").setViewName("hello");
	        registry.addViewController("/login").setViewName("login");
	        registry.addViewController("/403").setViewName("403");
	}
	
	/*
	public DataSource  dataSource(){
		System.out.println("***********DATASOURCE***********************************");
		DataSource ds= new DataSource();
		//DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/springbootdb");
		ds.setUsername("andrew");
		ds.setPassword("agnusdei");
		System.out.println("*********************************************************");
		return ds;
	}
	*/
	
	/*
	@Bean(name="userDetailsService")
	public UserDetailsService userDetailsService(){
		System.out.println("***********userDetailsService***********************************");
		JdbcDaoImpl jdbcImpl=new JdbcDaoImpl();
		jdbcImpl.setDataSource(dataSource());
		jdbcImpl.setUsersByUsernameQuery("select username, password from users where username=?");
		jdbcImpl.setAuthoritiesByUsernameQuery("select t1.username, t2.role from users t1 , user_roles t2  where t1.userid=t2.userid and t1.username=?");
		return jdbcImpl;
	}
	*/
	
	
}
