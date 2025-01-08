package tech.sam.ms_naissances;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(
		exclude={
				SecurityAutoConfiguration.class,
				ManagementWebSecurityAutoConfiguration.class,
				DataSourceTransactionManagerAutoConfiguration.class,
				DataSourceAutoConfiguration.class,
				HibernateJpaAutoConfiguration.class
		}
)
public class MsNaissancesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsNaissancesApplication.class, args);
	}

}
