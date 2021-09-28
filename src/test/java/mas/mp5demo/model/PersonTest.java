package mas.mp5demo.model;
import mas.mp5demo.repository.ClientRepository;
import mas.mp5demo.repository.PersonRepository;
import mas.mp5demo.repository.WorkerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PersonTest {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private WorkerRepository workerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Worker worker;
    Client client1, client2;

    @BeforeEach
    public void InitData() {
        worker = Worker.builder().name("workername").surname("workerSurname").income(200.0).build();
        client1 = Client.builder().name("client1name").surname("client1surname").phoneNumber("48958389868").build();
        client2 = Client.builder().name("client2name").surname("client2surname").phoneNumber("48303944039").build();
    }

    @Test
    public void requiredDependenciesTest() {

        assertNotNull(personRepository);
        assertNotNull(clientRepository);
        assertNotNull(workerRepository);
    }

    @Test
    public void saveAllTest() {
        saving();

        assertEquals(3, personRepository.count());
    }

    private void saving() {
        workerRepository.save(worker);
        clientRepository.save(client1);
        clientRepository.save(client2);
        entityManager.flush();
    }

    @Test
    public void functionTest() {
        saving();
        List<Worker> byIncomeGreaterThan = workerRepository.findByIncomeGreaterThan(150.0);
        assertEquals(1, byIncomeGreaterThan.size());
        System.out.println(byIncomeGreaterThan);
        worker.work();
        workerRepository.save(worker);
        entityManager.flush();
        byIncomeGreaterThan = workerRepository.findByIncomeGreaterThan(350.0);
        assertEquals(1, byIncomeGreaterThan.size());
        System.out.println(byIncomeGreaterThan);
    }

}