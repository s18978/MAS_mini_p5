package mas.mp5demo;
import lombok.RequiredArgsConstructor;
import mas.mp5demo.model.Shop;
import mas.mp5demo.model.Worker;
import mas.mp5demo.repository.ShopRepository;
import mas.mp5demo.repository.WorkerRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final ShopRepository shopRepository;
    private final WorkerRepository workersRepository;

    @EventListener
    public void atTheBeginning(ContextRefreshedEvent e) {
        System.out.println("context refreshed");
        Shop sh = Shop.builder().shopName("Shop 1 ").build();
        Worker w = Worker.builder().name("name").surname("surname").income(345).build();
        w.setWorkingPlace(sh);
        sh.getWorkers().add(w);
        shopRepository.save(sh);
        workersRepository.save(w);
    }
}