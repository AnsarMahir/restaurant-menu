package com.toticone.restuarant_menu.controller;

import com.toticone.restuarant_menu.dto.BasicProductDTO;
import com.toticone.restuarant_menu.dto.ExtraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // This method will broadcast to all subscribers when called
    public void sendProductUpdate(BasicProductDTO product) {
        messagingTemplate.convertAndSend("/topic/products/update", product);
    }

    public void sendProductDelete(int productId) {
        messagingTemplate.convertAndSend("/topic/products/delete", productId);
    }

    public void sendExtraUpdate(ExtraDTO extra) {
        messagingTemplate.convertAndSend("/topic/extras/update", extra);
    }

    public void sendExtraDelete(int extraId) {
        messagingTemplate.convertAndSend("/topic/extras/delete", extraId);
    }

    // You can also receive messages from clients
    @MessageMapping("/products/refresh")
    @SendTo("/topic/products/all")
    public String refreshProducts() {
        return "refresh";
    }
}
