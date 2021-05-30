package com.ZuulConsumer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Controller
public class ConsumerControllerClient {

    @Autowired
    private DiscoveryClient discoveryClient;

    public void getEmployee() throws RestClientException, IOException {

        ServiceInstance serviceInstance = discoveryClient.getInstances("Zuul").get(0);

        String baseUrl = serviceInstance.getUri().toString();

        String producerURL = baseUrl + "/producer/employee";
        String myserviceURL = baseUrl + "/myservice/hello";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(producerURL, HttpMethod.GET, getHeaders(), String.class);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        System.out.println("\nFetching from Producer");
        System.out.println(response.getBody());

        RestTemplate restTemplate2 = new RestTemplate();
        ResponseEntity<String> response2 = null;
        try {
            response2 = restTemplate2.exchange(myserviceURL, HttpMethod.GET, getHeaders(), String.class);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        System.out.println("\nFetching from MyService");
        System.out.println(response2.getBody());



    }

    private static HttpEntity<?> getHeaders() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }
}