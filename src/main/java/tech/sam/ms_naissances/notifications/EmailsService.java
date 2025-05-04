package tech.sam.ms_naissances.notifications;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tech.sam.ms_naissances.declarations.DeclarationStatus;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class EmailsService {
    private final MailpitClient mailpitClient;
    String senderEmail = "serge@mesnaissances.com";
    String senderName = "Serge de mesnaissances.com";

    public EmailsService(MailpitClient mailpitClient) {
        this.mailpitClient = mailpitClient;
    }

    public void send(Map<String, String> parameters) {
        String message = this.buildMessage(parameters);
        log.info("Le message est {}", message);

        Map<String, Object> emailParameters = Map.of(
              "Subject", "Votre code d'activation",
              "HTML", message,
              "text", message,
              "From",  Map.of("Email",senderEmail, "Name", senderName),
              "To", List.of(Map.of("Email", parameters.get("email"), "Name", parameters.get("name")))
        );
        this.mailpitClient.send(emailParameters);
    }

    private String buildMessage(Map<String, String> parameters) {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(this.getClass(), "/templates");
        configuration.setObjectWrapper(new DefaultObjectWrapper());

        try {
            Template template = configuration.getTemplate(parameters.get("template"));
            StringWriter stringWriter = new StringWriter();
            Map<String, String> templateParameters = Map.of(
                    "name", parameters.get("name"), 
                    "code", parameters.get("code")
            );
            template.process(templateParameters, stringWriter);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendStatusNotification(DeclarationStatus declarationStatus) {
        String message = String.format(
                """
                    Bonjour %s %s, <br />
                    Votre déclaration a été traitée. <br />
                    Elle est désormais %s<br />
                    Cordialement,
                    %s
                """,
                declarationStatus.getDeclaration().getFirstParent().getFirstName(),
                declarationStatus.getDeclaration().getFirstParent().getLastName(),
                declarationStatus.getStatus().getName(),
                senderName
        );

        Map<String, Object> emailParameters = Map.of(
                "Subject", "Mis à jour de votre déclaration",
                "HTML", message,
                "text", message,
                "From",  Map.of("Email",senderEmail, "Name", senderName),
                "To", List.of(Map.of(
                        "Email", declarationStatus.getDeclaration().getFirstParent().getEmail(),
                        "Name", String.format(
                                "%s %s",
                                declarationStatus.getDeclaration().getFirstParent().getFirstName(),
                                declarationStatus.getDeclaration().getFirstParent().getLastName())
                        ))
        );
        this.mailpitClient.send(emailParameters);
    }
}
