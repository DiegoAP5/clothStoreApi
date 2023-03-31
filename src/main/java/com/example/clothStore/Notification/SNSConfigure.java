package com.example.clothStore.Notification;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SNSConfigure {

    public static final String SECRET_KEY = "xiCDz3RtqvkHzWXX7iJu0gR9szbXlub/bw+TQP2H";
    public static final String ACCESS_KEY = "AKIAT5X2OFPJZURIKEIZ";

    @Primary
    @Bean
    public AmazonSNSClient amazonSNSClient() {
        return (AmazonSNSClient) AmazonSNSClientBuilder.standard().withRegion(Regions.US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY,
                        SECRET_KEY)))
                .build();
    }
}
