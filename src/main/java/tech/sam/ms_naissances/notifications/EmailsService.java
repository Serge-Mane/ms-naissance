package tech.sam.ms_naissances.notifications;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class EmailsService {
    private MailpitClient mailpitClient;
    String senderEmail="serge@mesnaissances.com";
    String senderName="serge de mesnaissances.com";

    public EmailsService(MailpitClient mailpitClient) {
        this.mailpitClient = mailpitClient;
    }

    public void send(Map<String,String> parameters){
        String message=this.buildMessage(parameters);
        log.info("Le message est {}",message);
//creation de l'objet qui va appeller send du mailpit
        Map<String,Object>emailParameters=Map.of(
                "Subject","Votre code d'activation",
                "HTML",message,
                "text",message,
                "From",Map.of(
                        "Email",senderEmail,
                        "Name",senderName
                ),
                "To", List.of(Map.of(
                        "Email",parameters.get("email"),
                        "Name",parameters.get("name"))
                )
        );
        this.mailpitClient.send(emailParameters);
    }

    //le corp de mon mail
    private String buildMessage(Map<String,String> parameters){
        Configuration configuration=new Configuration();
        //pour tous mes templates de mails il faut aller dans ce repertoire /templates
        configuration.setClassForTemplateLoading(this.getClass(),"/templates");
        configuration.setObjectWrapper(new DefaultObjectWrapper());

        try {
            Template template=configuration.getTemplate(parameters.get("template"));
            StringWriter stringWriter=new StringWriter();
            Map<String, String> templatesParameters=Map.of("name",parameters.get("name"),"code",parameters.get("code"));
            template.process(templatesParameters,stringWriter);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
