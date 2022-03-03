package tranduongkyoto.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
    public Iterable<Taco> recentTacos(){
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return tacoRepository.findAll(page).getContent();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco){
        return tacoRepository.save(taco);
    }

    @GetMapping("/{id}")
    public Taco tocaById(@PathVariable("id") Long id){
        Optional<Taco> optionalTaco = tacoRepository.findById(id);
        if(optionalTaco.isPresent()){
            return optionalTaco.get();
        }
        return null;
    }
}
