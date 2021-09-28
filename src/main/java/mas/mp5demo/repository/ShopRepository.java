package mas.mp5demo.repository;
import mas.mp5demo.model.Shop;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ShopRepository extends CrudRepository<Shop,Long> {

    public List<Shop> findByShopName(String name);

    public List<Shop> findByShopNameStartsWith (String startsWith);



}