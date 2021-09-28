package mas.mp5demo.model;
import mas.mp5demo.repository.ShopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ShopTest {

    @Autowired
    private ShopRepository shopRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Shop s1;
    Shop s2;
    Shop s3;

    @BeforeEach
    public void initData() {
        s1 = Shop.builder().shopName("shop1").build();
        s2 = Shop.builder().shopName("shop2").build();
        s3 = Shop.builder().shopName("market").build();
    }

    @Test
    public void requiredDependenciesTest() {
        assertNotNull(shopRepository);
    }
    @Test
    public void getShopsTest() {
        Iterable<Shop> shops = shopRepository.findAll();
        for(Shop s: shops) {
            System.out.println(s);
        }
    }
    @Test
    public void saveShopTest() {
        shopRepository.save(s1);
        entityManager.flush();
        long count = shopRepository.count();
        assertEquals(1, count);
    }
    @Test
    public void saveInvalidNamedAndSizedShopTest() {
        assertThrows(ConstraintViolationException.class, () -> {
            s1.setShopName("");
            s2.setShopName("shop2");
            shopRepository.save(s1);
            shopRepository.save(s2);
            entityManager.flush();
        });
    }
    void saveShops() {
        shopRepository.save(s1);
        shopRepository.save(s2);
        shopRepository.save(s3);
        entityManager.flush();
    }
    @Test
    public void findShopByNameTest() {
        saveShops();

        List<Shop> s2 = shopRepository.findByShopName("shop1");
        assertEquals(1, s2.size());
    }
    @Test
    public void findByShopNameStartsWithTest() {
        saveShops();

        List<Shop> test = shopRepository.findByShopNameStartsWith("sh");
        System.out.println(test);
        assertEquals(2, test.size());
    }
}