package selly.authenticate.advice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorExceptionDto {
    private String message;
    private String errorCode;
}
