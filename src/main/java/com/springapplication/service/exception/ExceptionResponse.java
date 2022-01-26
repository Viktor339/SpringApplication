package com.springapplication.service.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class ExceptionResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;

    @Override
    public String toString() {
        return "{" +
                "timestamp=" + timestamp +
                ", status=" + status +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
