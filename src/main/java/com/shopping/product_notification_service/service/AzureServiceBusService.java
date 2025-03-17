package com.shopping.product_notification_service.service;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.azure.messaging.servicebus.ServiceBusMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AzureServiceBusService {

    @Value("${azure.servicebus.connection-string}")
    private String connectionString;

    @Value("${azure.servicebus.queue-name}")
    private String queueName;

    public void sendMessage(String messageContent) {
        // Create a sender client for the Azure Service Bus Queue
        ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .sender()
                .queueName(queueName)
                .buildClient();

        // Create a Service Bus message
        ServiceBusMessage message = new ServiceBusMessage(messageContent);

        // Send the message to the Service Bus
        senderClient.sendMessage(message);
        System.out.println("Message sent to Azure Service Bus: " + messageContent);
    }
}

//public static void sendMessage() {
//    try {
//        // Create a token using the default Azure credential
//        DefaultAzureCredenti credential = new DefaultAzureCredentialBuilder()
//                .build();
//
//        // Create the ServiceBusSenderClient
//        ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
//                .fullyQualifiedNamespace(FULLY_QUALIFIED_NAMESPACE)
//                .credential(credential)
//                .sender()
//                .queueName(QUEUE_NAME)
//                .buildClient();
//
//        // Create the ServiceBusMessage you want to send
//        ServiceBusMessage message = new ServiceBusMessage("Hello, World!");
//
//        // Send the message to the queue
//        senderClient.sendMessage(message);
//        System.out.println("Sent a single message to the queue: " + QUEUE_NAME);
//
//        // Close the sender client after use (important for releasing resources)
//        senderClient.close();
//    } catch (ManagementException e) {
//        System.err.println("Management Exception occurred: " + e.getMessage());
//    } catch (Exception e) {
//        System.err.println("Exception occurred while sending message: " + e.getMessage());
//    }
//}


