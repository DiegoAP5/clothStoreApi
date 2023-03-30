package com.example.clothStore.services;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.example.clothStore.entities.Send;
import com.example.clothStore.entities.User;
import com.example.clothStore.services.interfaces.IOrderService;
import com.example.clothStore.services.interfaces.ISNSService;
import com.example.clothStore.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SNSServiceImpl implements ISNSService {
    @Autowired
    private AmazonSNSClient amazonSNSClient;

    @Override
    public void subscribeEmail(String topicArn, String email) {
        String filterPolicy = "{\"associatedEmail\":[\"equals\",\"" + email + "\"]}";
        SubscribeRequest request = new SubscribeRequest(topicArn, "email", email)
                .withProtocol("email")
                .withReturnSubscriptionArn(true)
                .withAttributes(Collections.singletonMap("FilterPolicy", filterPolicy));
        amazonSNSClient.subscribe(request);
    }

    @Override
    public void sendNotification(String message,  String subject, String email) {

        System.out.println(message);

        PublishRequest publishRequest = new PublishRequest()
                    .withTopicArn("")
                .withMessage(message)
                .withSubject(subject)
                .withMessageAttributes(generateEmailAttribute(email));
        amazonSNSClient.publish(publishRequest);
    }

    private Map<String, MessageAttributeValue> generateEmailAttribute(String email){
        Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
        messageAttributes.put("associatedEmail", new MessageAttributeValue().withDataType("String").withStringValue(String.valueOf(email)));
        return messageAttributes;
    }

}
