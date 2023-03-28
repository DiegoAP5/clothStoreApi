package com.example.clothStore.services.interfaces;

import com.example.clothStore.entities.Order;
import com.example.clothStore.entities.Send;
import com.example.clothStore.entities.Status;

public interface ISNSService {

    void subscribeEmail(String topicArn, String email);

    void sendNotification(Send send, String topicArn);

}
