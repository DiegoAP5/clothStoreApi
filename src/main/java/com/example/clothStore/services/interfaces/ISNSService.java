package com.example.clothStore.services.interfaces;

import com.example.clothStore.entities.Order;
import com.example.clothStore.entities.Send;
import com.example.clothStore.entities.Status;
import com.example.clothStore.entities.User;

public interface ISNSService {

    void subscribeEmail(String topicArn, String email);

    void sendNotification(String message, String subject, String email);

}
