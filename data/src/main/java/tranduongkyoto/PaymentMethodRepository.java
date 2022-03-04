package tranduongkyoto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Mono;


public interface PaymentMethodRepository
        extends ReactiveCrudRepository<PaymentMethod, String> {
    Mono<PaymentMethod> findByUserId(String userId);
}
