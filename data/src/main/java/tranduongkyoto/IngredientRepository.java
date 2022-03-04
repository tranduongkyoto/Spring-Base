package tranduongkyoto;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
public interface IngredientRepository extends ReactiveCrudRepository<Ingredient, String> {
}
