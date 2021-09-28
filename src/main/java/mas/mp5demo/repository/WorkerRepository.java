package mas.mp5demo.repository;
import mas.mp5demo.model.Worker;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkerRepository extends CrudRepository<Worker, Long> {

    public List<Worker> findByIncomeGreaterThan(double income);

}