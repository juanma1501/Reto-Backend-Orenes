package retoBackendOrenes.RetoBackend.shared;

import lombok.Data;

@Data
public class ResponseWrapper<T> {
    private T response;
    private String code;
    private ResponseMessage message;
}
