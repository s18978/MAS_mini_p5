package mas.mp5demo.model;
import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class Worker extends Person {

    @Min(200)
    private double income;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Shop workingPlace;

    public void work() {
        System.out.println("working");
        income += income;
    }
}