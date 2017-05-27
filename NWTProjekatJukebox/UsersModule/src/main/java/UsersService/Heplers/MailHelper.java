package UsersService.Heplers;

import java.io.IOException;
import com.sendgrid.*;

/**
 * Created by Šahin on 22.5.2017.
 */
public class MailHelper {
    public static void sendMail(String toEmail, String msg) throws IOException {

        Email from = new Email("nwtjukebox@gmail.com");
        String subject = "Register confirmation";
        Email to = new Email(toEmail);
        Content content = new Content("text/plain", msg);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}
