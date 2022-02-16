package tranduongkyoto;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class RabbitMessagingService implements MessagingService {
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMessagingService(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate =rabbitTemplate;
    }


    @Override
    public void sendOrder(Order order) {
        rabbitTemplate.convertAndSend("order", order, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties props = message.getMessageProperties();
                props.setHeader("X_ORDER_SOURCE", "WEB");
                return message;
            }
        });
    }

}
