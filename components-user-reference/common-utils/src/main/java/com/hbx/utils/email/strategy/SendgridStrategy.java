package com.hbx.utils.email.strategy;

import com.hbx.utils.email.dto.MailSendDTO;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

/**
 * 三方邮件服务和库，比如SendGrid
 */
@Slf4j
public class SendgridStrategy implements MailStrategy{
    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;
    @Override
    public void send(MailSendDTO mailSendDTO) throws IOException {
        Email from = new Email(mailSendDTO.getUsername());
        Email toEmail = new Email(mailSendDTO.getTo());
        Content content = new Content("text/plain", mailSendDTO.getText());
        Mail mail = new Mail(from, mailSendDTO.getSubject(), toEmail, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            log.info("send email success,resp:{}",response);
        } catch (IOException ex) {
            log.info("send email err,{}",ex.getMessage());
            throw ex;
        }
    }
}
