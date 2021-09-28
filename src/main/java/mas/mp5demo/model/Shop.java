package mas.mp5demo.model;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "shop name should not be null")
    @Size(min = 2, max = 20)
    private String shopName;

    @OneToMany(mappedBy = "workingPlace", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Worker> workers = new HashSet<>();

    @OneToMany(mappedBy = "shop", cascade = {CascadeType.REMOVE})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<FirstPurchase> firstPurchase = new HashSet<>();

}