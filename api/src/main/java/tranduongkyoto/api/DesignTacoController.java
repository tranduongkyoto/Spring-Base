package tranduongkyoto.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tranduongkyoto.Taco;
import tranduongkyoto.TacoRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping(path="/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {
    private TacoRepository tacoRepository;

    public DesignTacoController(TacoRepository tacoRepository){
        this.tacoRepository = tacoRepository;
    }

    @GetMapping("/recent")
    public Flux<Taco> recentTacos(){
        return tacoRepository.findAll().take(2);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Taco> postTaco(@RequestBody Taco taco){
        return tacoRepository.save(taco);
    }

    @GetMapping("/{id}")
    public Mono<Taco> tocaById(@PathVariable("id") Long id){
        return tacoRepository.findById(id);
    }
}
