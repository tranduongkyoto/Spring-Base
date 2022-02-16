package tranduongkyoto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RootApp {
    public static void main(String[] args) {
        SpringApplication.run(RootApp.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader2(){
        return args -> System.out.println("Command line runner test!!!");
    }
}
