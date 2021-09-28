package mas.mp5demo.model;
import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class Client extends Person {

    @NotBlank
    private String phoneNumber;

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<FirstPurchase> firstPurchase = new HashSet<>();

    public void purchase() {
        System.out.println("purchasing");
    }
}