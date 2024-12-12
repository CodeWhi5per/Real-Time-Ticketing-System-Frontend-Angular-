package com.example.Real.Time.Ticketing.System.enums;

public enum RtsError {

    SYSTEM_CONFIGURATION_SAVED_FAILED("RTS-001", "System Configuration Saving Failed"),
    SYSTEM_DATA_LOAD_FAILED("RTS-002", "System Data Loading Failed"),
    TRANSACTIONS_SAVED_FAILED("RTS-003", "Transaction Saving Failed"),
    COUNT_UPDATE_FAILED("RTS-004", "Count Update Failed"),
    COUNT_LOAD_FAILED("RTS-005", "Count Loading Failed"),;

    private String errorCode;
    private String errorMessage;

    RtsError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
