package com.shopping.product_notification_service.Controller;

import com.shopping.product_notification_service.service.AzureServiceBusService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = {"http://localhost:3000", "https://annxappservice-esbgd9fbahcbe8fn.centralindia-01.azurewebsites.net"})
@RestController
public class MessageController {

    private final AzureServiceBusService azureServiceBusService;

    // Constructor-based Dependency Injection
    @Autowired
    public MessageController(AzureServiceBusService azureServiceBusService) {
        this.azureServiceBusService = azureServiceBusService;
    }

    // POST endpoint to send a message to Azure Service Bus
    @PostMapping("/sendMessage")
    public String sendMessage(@RequestBody String messageContent) {
        try {
            // Calling the service method to send the message to Azure Service Bus
            azureServiceBusService.sendMessage(messageContent);
            return "Message sent successfully to Azure Service Bus!";
        } catch (Exception e) {
            // Handling any errors that may occur
            return "Error sending message to Azure Service Bus: " + e.getMessage();
        }
    }
}

