package tech.sam.ms_naissances;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import tech.sam.ms_naissances.security.RsaKeys;

@EnableConfigurationProperties(RsaKeys.class)
@SpringBootApplication(
		exclude={
				SecurityAutoConfiguration.class,
				ManagementWebSecurityAutoConfiguration.class,
		}
)
public class MsNaissancesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsNaissancesApplication.class, args);
	}

}
