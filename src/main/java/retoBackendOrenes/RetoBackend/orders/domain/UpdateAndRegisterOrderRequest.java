package retoBackendOrenes.RetoBackend.orders.domain;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateAndRegisterOrderRequest {
    private String OrderNumber;
    private String client;
    private Date creationDate;
    private Date deliveryDate;
    private String originAddress;
    private String deliveryAddress;
    private int weight;
}
