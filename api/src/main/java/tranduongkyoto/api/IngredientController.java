package tranduongkyoto.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tranduongkyoto.Ingredient;
import tranduongkyoto.IngredientRepository;

import java.net.URI;

@RestController
@RequestMapping(path = "/ingredients", produces = "application/json")
@CrossOrigin(origins = "*")
public class IngredientController {
    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientController(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public Flux<Ingredient> allIngredients(){
        return ingredientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Ingredient> byId(@PathVariable String id) {
        return ingredientRepository.findById(id);
    }

    @PutMapping("/{id}")
    public void updateIngredient(@PathVariable String id, @RequestBody Ingredient ingredient) {
        if (!ingredient.getId().equals(id)) {
            throw new IllegalStateException("Given ingredient's ID doesn't match the ID in the path.");
        }
        ingredientRepository.save(ingredient);
    }

    @PostMapping
    public Mono<ResponseEntity<Ingredient>> postIngredient(@RequestBody Mono<Ingredient> ingredient) {
        return ingredient
                .flatMap(ingredientRepository::save)
                .map(i -> {
                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.setLocation(URI.create("http://localhost:8080/ingredients/" + i.getId()));
                    return new ResponseEntity<Ingredient>(i, httpHeaders, HttpStatus.CREATED);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable String id) {
        ingredientRepository.deleteById(id);
    }
}
