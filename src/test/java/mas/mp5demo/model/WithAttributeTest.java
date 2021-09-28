package mas.mp5demo.model;
import mas.mp5demo.repository.ClientRepository;
import mas.mp5demo.repository.FirstPurchaseRepository;
import mas.mp5demo.repository.ShopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class WithAttributeTest {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private FirstPurchaseRepository firstPurchaseRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Shop s1;
    Client c1;
    FirstPurchase fp;

    @Test
    public void requiredDependenciesTest() {
        assertNotNull(shopRepository);
        assertNotNull(clientRepository);
        assertNotNull(firstPurchaseRepository);
    }
    @BeforeEach
    public void InitData() {
        s1 = Shop.builder().shopName("shopName").build();
        c1 = Client.builder().name("name").surname("surname").phoneNumber("48593750274").build();
        fp = FirstPurchase.builder().purchaseDate(LocalDate.now()).price(200.2).client(c1).shop(s1).build();
        s1.getFirstPurchase().add(fp);
        c1.getFirstPurchase().add(fp);
        shopRepository.save(s1);
        clientRepository.save(c1);
        firstPurchaseRepository.save(fp);
        entityManager.flush();
    }
    @Test
    public void test() {
        Iterable<FirstPurchase> all = firstPurchaseRepository.findAll();
        for (FirstPurchase f : all) {
            System.out.println(f);
        }
        shopRepository.delete(s1);
        clientRepository.delete(c1);
        entityManager.flush();

        all = firstPurchaseRepository.findAll();
        for (FirstPurchase f : all) {
            System.out.println("-----------" + f);
        }
    }
}