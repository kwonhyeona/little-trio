package kr.or.hanium.probono.littletrio.error;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

