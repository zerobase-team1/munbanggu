package com.zerobase.munbanggu.user.controller;

import com.zerobase.munbanggu.user.service.SendMailService;
import com.zerobase.munbanggu.user.service.SendMessageService;
import com.zerobase.munbanggu.user.type.AuthenticationStatus;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final SendMailService sendMailService;
    private final SendMessageService sendMessageService;

    @PostMapping("/email-send") //이메일 발송
    public ResponseEntity<AuthenticationStatus> sendMail(@RequestBody Map<String,String> req){
        return ResponseEntity.ok(sendMailService.sendMailVerification(req.get("email")));
    }

    @PostMapping("/email-auth") //이메일 인증
    public ResponseEntity<AuthenticationStatus> verifyMail(@RequestBody Map<String,String> req){
        return ResponseEntity.ok(sendMailService.verifyCode(req.get("email"),req.get("code")));
    }

    @PostMapping("/phone-send") // 핸드폰 인증번호 발송
    public ResponseEntity<AuthenticationStatus> sendSMS(@RequestBody Map<String,String> req){
        return ResponseEntity.ok(sendMessageService.sendMessage(req.get("phoneNumber")));
    }

    @PostMapping("/phone-auth") // 핸드폰 인증
    public ResponseEntity<AuthenticationStatus> verifySMS(@RequestBody Map<String,String> req){
        return ResponseEntity.ok(sendMessageService.verifyCode(req.get("phoneNumber"),req.get("code")));
    }

}
