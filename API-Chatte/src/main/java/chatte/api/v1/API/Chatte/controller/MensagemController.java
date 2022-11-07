package chatte.api.v1.API.Chatte.controller;

import chatte.api.v1.API.Chatte.entities.Mensagem;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Mensagem Rest Controller", description = "Responsável por gerir mensagens nesse projeto")
@RestController
@RequestMapping("/api/v1/mensagens")
public class MensagemController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String enviarMensagem(@Payload Mensagem mensagem) {
        System.out.println("mensagem enviada! " + mensagem.getCorpo());
        return mensagem.getCorpo();
    }
}
