import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClient {
    private RestTemplate restTemplate;

    public RestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String test(){
        String url = "https://jsonplaceholder.typicode.com/posts";
        return this.restTemplate.getForObject(url, String.class);
    }
}
