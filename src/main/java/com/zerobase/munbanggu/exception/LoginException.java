package com.zerobase.munbanggu.exception;


import com.zerobase.munbanggu.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginException extends RuntimeException{

    private ErrorCode errorCode;
    private String message;

    public LoginException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getDescription();
    }

}