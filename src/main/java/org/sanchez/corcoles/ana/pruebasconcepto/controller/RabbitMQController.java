package org.sanchez.corcoles.ana.pruebasconcepto.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RabbitMQController {

    private static final String QUEUENAME = "micola";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/buscarUsuario")
    public String insertarMensaje(@RequestParam("mensaje") String mensaje){
        rabbitTemplate.convertAndSend(QUEUENAME, mensaje); //Escribir
        return "Mensaje '"+mensaje+"' insertado";
    }

    @RabbitListener(queues = QUEUENAME) //Leer
    public void consumirMensaje(String mensaje){
        System.out.println("Se ha consumido el mensaje: "+mensaje);
    }


}
