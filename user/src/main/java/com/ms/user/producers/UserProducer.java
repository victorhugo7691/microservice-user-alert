package com.ms.user.producers;

import com.ms.user.dtos.EmailRecordDto;
import com.ms.user.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${broker.queue.email.name}")
    private String routingKey;

    public UserProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishMessageEmail(UserModel userModel){
        var emailDto = new EmailRecordDto(
                userModel.getUserId(),
                userModel.getEmail(),
                "Cadastro realizado com sucesso!",
                userModel.getNome() + " seja bem vindo(a)");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }

}
