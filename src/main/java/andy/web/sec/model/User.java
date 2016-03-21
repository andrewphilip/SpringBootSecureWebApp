package andy.web.sec.model;

import java.io.Serializable;

//@Entity
//@Table(name="users")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//@Id
	//@Column(name="username")
	private String userName;
	
	//@Column(name="password")
	private String password;
	
	
}
