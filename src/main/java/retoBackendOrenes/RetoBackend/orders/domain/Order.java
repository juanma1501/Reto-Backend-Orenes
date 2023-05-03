package retoBackendOrenes.RetoBackend.orders.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
@Document( collection = "orders")
public class Order {
    @Id
    private String orderNumber;

    private String userId;

    private Date creationDate;

    private Date deliveryDate;

    private String originAddress;

    private String deliveryAddress;

    private int weight;
}
