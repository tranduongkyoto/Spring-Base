package tranduongkyoto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RootApp {
    public static void main(String[] args) {
        SpringApplication.run(RootApp.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader2(){
        return args -> {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://pokeapi.co/api/v2/pokemon/ditto";
            System.out.println(restTemplate.getForObject(url, String.class));
            System.out.println("Command line runner test!!!");
        };
    }
}
