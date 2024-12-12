package com.example.Real.Time.Ticketing.System.exeption;

import com.example.Real.Time.Ticketing.System.enums.RtsError;

public class RtsExeption extends RuntimeException {

    private String errorCode;
    private String errorMessage;

    public RtsExeption(RtsError rtsError) {
        this.errorCode = rtsError.getErrorCode();
        this.errorMessage = rtsError.getErrorMessage();
    }

    public RtsExeption(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
