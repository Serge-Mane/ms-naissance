package tech.sam.ms_naissances.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import tech.sam.ms_naissances.notifications.MailpitClient;

@Configuration
public class ApplicationConfigurations {
    String mailClientUrl ="http://training.mails.chillo.fr";

    @Bean
    MailpitClient mailpitClient(){
        RestClient client=RestClient.create(mailClientUrl);
        HttpServiceProxyFactory httpServiceProxyFactory=HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(client))

                .build();
        return httpServiceProxyFactory.createClient(MailpitClient.class);
    }
}
