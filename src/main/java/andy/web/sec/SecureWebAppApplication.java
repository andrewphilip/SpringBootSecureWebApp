package andy.web.sec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories(basePackages="andy.web.sec.repo")
//@EntityScan(basePackages="andy.web.sec.model")
public class SecureWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureWebAppApplication.class, args);
	}
}
