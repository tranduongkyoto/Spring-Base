package tranduongkyoto;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMessagingReceiver implements MessagingReceiver {
    private RabbitTemplate rabbitTemplate;

    public RabbitMessagingReceiver(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public Order receiveOrder(){
        return (Order) rabbitTemplate.receiveAndConvert("order");
    }
}
