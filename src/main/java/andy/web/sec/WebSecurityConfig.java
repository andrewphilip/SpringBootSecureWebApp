package andy.web.sec;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private MyAuthenticationService myAuthenticationService ;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/","/home","/login").permitAll()
				.antMatchers("/hello").access("hasRole('ROLE_admin')") //.hasRole("admin")   //access("hasRole('admin')")
				.anyRequest().authenticated()
				.and()	
				.formLogin()
					.loginPage("/login")
					.usernameParameter("username").passwordParameter("password")
					.and()
				.logout()
				.and()
				//.exceptionHandling().accessDeniedPage("/403")
				//.and()
					.csrf();
	}
	
	/* @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
		auth
			.inMemoryAuthentication()
				.withUser("andrew").password("agnusdei").roles("USER");
		
		
	}
	*/
	
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
		System.out.println("************inside configAuthentication*********************");
		auth.userDetailsService(myAuthenticationService).passwordEncoder(passwordEncoder());
		/* auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("select username, password from users where username=?")
			.authoritiesByUsernameQuery("select userid, role from user_roles where username=?");
		*/
		System.out.println("*********************END*************************************");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public DataSource  getDataSource(){
		System.out.println("***********DATASOURCE***********************************");
		BasicDataSource ds= new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://192.168.1.9:3306/andy?autoReconnect=true&useSSL=false");
		ds.setUsername("andrew");
		ds.setPassword("agnusdei");
		
		try{
			String username=ds.getConnection().getMetaData().getUserName();
			String url=ds.getConnection().getMetaData().getURL();
			System.out.println("database.username : "+username);
			System.out.println("database.url : "+url);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("*********************************************************");
		return ds;
	}
	
}
