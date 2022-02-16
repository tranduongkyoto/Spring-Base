package tranduongkyoto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.mail.dsl.Mail;

@Configuration
public class EmailItegrationConfig {
    @Bean
    public IntegrationFlow EmailFlow(
            EmailProperties emailProperties,
            EmailToOrderTransformer emailToOrderTransformer,
            OrderSubmitMessageHandler orderSubmitMessageHandler){
        return IntegrationFlows
                .from(Mail.imapInboundAdapter(emailProperties.getImapUrl()),
                        e -> e.poller(
                                Pollers.fixedDelay(emailProperties.getPollRate())))
                .transform(emailToOrderTransformer)
                .handle(orderSubmitMessageHandler)
                .get();
    }
}
