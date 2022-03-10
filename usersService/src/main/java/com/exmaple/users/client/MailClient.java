package com.exmaple.users.client;

import com.exmaple.users.config.MailClientConfig;
import com.exmaple.users.models.ConfirmRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "mailclient", url = "http://192.168.178.15:8082/", configuration = MailClientConfig.class)
public interface MailClient {

    @RequestMapping(method = RequestMethod.POST, value = "/mail", produces = "application/json")
    ResponseEntity<String> sendMail(@RequestBody ConfirmRequest confirmRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/mail/test", produces = "application/json")
    ResponseEntity<Void> testMail();
}
