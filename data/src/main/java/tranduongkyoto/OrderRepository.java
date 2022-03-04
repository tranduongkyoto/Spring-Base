package tranduongkyoto;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;


public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {
    Flux<Order> findByUserOrderByPlacedAtDesc(
            User user, Pageable pageable);
}
