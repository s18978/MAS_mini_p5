package mas.mp5demo.model;
import mas.mp5demo.model.Person;
import mas.mp5demo.model.Shop;
import mas.mp5demo.repository.PersonRepository;
import mas.mp5demo.repository.ShopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class BasicAssociationTest {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private PersonRepository personRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Shop s1;
    Worker w1;

    @Test
    public void requiredDependenciesTest() {
        assertNotNull(shopRepository);
        assertNotNull(personRepository);
    }
    @BeforeEach
    public void InitData() {
        s1 = Shop.builder().shopName("shopName").build();
        w1 = Worker.builder().name("name").surname("surname").income(300).build();
    }
    @Test
    public void saveTest() {
        s1.getWorkers().add(w1);
        shopRepository.save(s1);
        w1.setWorkingPlace(s1);
        personRepository.save(w1);
        entityManager.flush();

        Optional<Shop> byId = shopRepository.findById(s1.getId());
        assertTrue(byId.isPresent());
        System.out.println(byId.get().getWorkers());
        assertEquals(1, byId.get().getWorkers().size());
    }
}