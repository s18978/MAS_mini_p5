package mas.mp5demo.model;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"client_id", "shop_id"})})
@ToString
public class FirstPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @NotNull
    private Client client;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    @NotNull
    private Shop shop;

    @NotNull
    private LocalDate purchaseDate;
    @Min(0)
    private double price;

}