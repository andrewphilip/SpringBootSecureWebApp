package andy.web.sec;

import java.sql.SQLException;
import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import andy.web.sec.model.UserInfo;

@Component
public class MyAuthenticationService implements UserDetailsService {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Inside loadUserByUsername().........................:"+ username);
		String query="select t1.username, password, rolename from users t1 inner join userroles t2 on ( t1.username =t2.username) and t1.username=?";
		/*try{
			String url=jdbcTemplate.getDataSource().getConnection().getMetaData().getURL();
			System.out.println("database.url : "+url);
		}catch(Exception e){
			e.printStackTrace();
		}
		*/
		UserInfo userInfo=jdbcTemplate.queryForObject(query,new Object[]{username},
				(rs,rnum) -> new UserInfo(rs.getString("USERNAME"),rs.getString("PASSWORD"),rs.getString("ROLENAME")));
		
		GrantedAuthority authority=new SimpleGrantedAuthority("ROLE_"+userInfo.getRole());
		UserDetails userDetails= (UserDetails)new User(userInfo.getUsername(),userInfo.getPassword(),Arrays.asList(authority));
		System.out.println("Authority: "+userDetails.getAuthorities().stream().findFirst().toString());
		return userDetails;
	}

}
