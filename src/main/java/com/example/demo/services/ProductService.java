package com.example.demo.services;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;

import java.math.BigDecimal;
import java.util.Date;

import java.util.UUID;

import java.time.LocalDateTime;


@RestController
@RequestMapping("product")
public class ProductService {
    
    //Get value from properties 

    @Value("${ASTRA_DB_ID}")
    private String ASTRA_DB_ID; 

    @Value("${ASTRA_DB_REGION}")
    private String ASTRA_DB_REGION; 

    @Value("${ASTRA_DB_KEYSPACE}")
    private String ASTRA_DB_KEYSPACE; 

    @Value("${ASTRA_DB_APPLICATION_TOKEN}")
    private String ASTRA_DB_APPLICATION_TOKEN;
    
    @GetMapping("/test")
    public Greeting greeting() throws Exception {
        
        System.out.println("ASTRA_DB_ID : " + ASTRA_DB_ID);
        System.out.println("ASTRA_DB_REGION : " + ASTRA_DB_REGION);
        System.out.println("ASTRA_DB_KEYSPACE : " + ASTRA_DB_KEYSPACE);
        System.out.println("ASTRA_DB_APPLICATION_TOKEN : " + ASTRA_DB_APPLICATION_TOKEN);

        return new Greeting(ASTRA_DB_ID);
    }

    private String getBaseURL() {
        return "https://" + ASTRA_DB_ID + "-" + ASTRA_DB_REGION + ".apps.astra.datastax.com/api/rest/v2/keyspaces/" + ASTRA_DB_KEYSPACE + "/rest_example_products/";
    }

    @GetMapping("/get/{id}")
    public Data getProduct(@PathVariable String id) {
        //https://www.geeksforgeeks.org/five-main-benefits-of-apache-cassandra/
        //https://www.baeldung.com/rest-template
        //https://howtodoinjava.com/spring-boot2/resttemplate/resttemplate-get-example/
        //https://spring.io/guides/tutorials/rest/
        try { 
            RestTemplate restTemplate = new RestTemplate();

            String baseUrl = getBaseURL() + id  + "?fields=description%2C%20productname%2Cid%2Cprice"; 

            URI uri = new URI(baseUrl);
        
            HttpHeaders headers = new HttpHeaders();
            //headers.set("content-type", "application/json");  
            headers.set("x-cassandra-token", ASTRA_DB_APPLICATION_TOKEN);
            
            HttpEntity<Data> requestEntity = new HttpEntity<>(null, headers);
            
            ResponseEntity<Data> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, Data.class);
                
            return result.getBody();
        } catch(java.net.URISyntaxException urlse) {
            urlse.printStackTrace();
        } 

        return null;
    }

    @PostMapping("/add")
    public String addProduct(@RequestBody Product newProduct) {
        try { 
            RestTemplate restTemplate = new RestTemplate();

            String baseUrl = getBaseURL(); 

            URI uri = new URI(baseUrl);
        
            HttpHeaders headers = new HttpHeaders();
            headers.set("content-type", "application/json");  
            headers.set("x-cassandra-token", ASTRA_DB_APPLICATION_TOKEN);
            
            newProduct.setId(UUID.randomUUID().toString());
            newProduct.setCreated(new Date());

            HttpEntity<Product> requestEntity = new HttpEntity<>(newProduct, headers);
            
            ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);
            return result.getBody();

        } catch(java.net.URISyntaxException urlse) {
            urlse.printStackTrace();
        } 

        return null;

    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        try { 
            RestTemplate restTemplate = new RestTemplate();

            String baseUrl = getBaseURL() + id; 

            URI uri = new URI(baseUrl);
        
            HttpHeaders headers = new HttpHeaders();
            headers.set("x-cassandra-token", ASTRA_DB_APPLICATION_TOKEN);
            
            HttpEntity<Data> requestEntity = new HttpEntity<>(null, headers);
            
            ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.DELETE, requestEntity, String.class);
                
            return result.getBody();
        } catch(java.net.URISyntaxException urlse) {
            urlse.printStackTrace();
        } 

        return null;
    }

    @PatchMapping("/update")
    public String updateProduct(@RequestBody Product product) { 

        PatchProduct patchProduct = new PatchProduct();
        patchProduct.setDescription(product.getDescription());
        patchProduct.setProductname(product.getProductname());
        patchProduct.setPrice(product.getPrice());
        try {
            RestTemplate restTemplate = new RestTemplate();

            String baseUrl = getBaseURL() + product.getId(); 

            System.out.println(baseUrl);

            URI uri = new URI(baseUrl);
        
            HttpHeaders headers = new HttpHeaders();
            headers.set("x-cassandra-token", ASTRA_DB_APPLICATION_TOKEN);
            headers.set("content-type", "application/json");  
            
            HttpEntity<PatchProduct> requestEntity = new HttpEntity<>(patchProduct, headers);
            
            ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.PUT, requestEntity, String.class);
                
            return result.getBody();
        } catch(java.net.URISyntaxException urlse) {
            urlse.printStackTrace();
        } 

        return null;
    } 

}
