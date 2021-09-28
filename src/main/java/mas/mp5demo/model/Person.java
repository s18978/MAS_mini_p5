package mas.mp5demo.model;
import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder

public abstract class Person {

    @NotBlank
    private String name;
    private String surname;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



}