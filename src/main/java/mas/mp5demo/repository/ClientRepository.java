package mas.mp5demo.repository;
import mas.mp5demo.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {

}