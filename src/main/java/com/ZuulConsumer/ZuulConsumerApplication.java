package com.ZuulConsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClientException;

import java.io.IOException;

@SpringBootApplication
public class ZuulConsumerApplication {

    public static void main(String[] args) throws RestClientException, IOException {
        ApplicationContext ctx = SpringApplication.run(ZuulConsumerApplication.class, args);

        ConsumerControllerClient consumerControllerClient = ctx.getBean(ConsumerControllerClient.class);
        System.out.println(consumerControllerClient);
        consumerControllerClient.getEmployee();
    }

    @Bean
    public ConsumerControllerClient consumerControllerClient() {
        return new ConsumerControllerClient();
    }

}
