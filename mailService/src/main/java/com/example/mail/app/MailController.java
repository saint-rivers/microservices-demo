package com.example.mail.app;

import com.example.mail.request.ConfirmRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {
    final private EmailServerImpl emailServer;

    public MailController(EmailServerImpl emailServer) {
        this.emailServer = emailServer;
    }

    @PostMapping
    public ResponseEntity<Void> sendConfirmationEmail(@RequestBody ConfirmRequest confirmRequest) throws Exception {

        // todo: only accept email a parameter and generate a one time password
        if (confirmRequest.getToken().isEmpty()) {
            confirmRequest.setToken("123123");
        }
        emailServer.sendMail(confirmRequest.getEmail(), confirmRequest.getToken());
        return ResponseEntity.ok().build();
    }
}
