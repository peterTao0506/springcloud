package com.tsx.springcloud.zuul.start;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableZuulProxy
@RestController
public class SpringCloudZuulApplicationStart 
{
	@Autowired
	private RestTemplate client;
	
	@Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
    	RestTemplate template = new RestTemplate();
        SimpleClientHttpRequestFactory factory = (SimpleClientHttpRequestFactory) template.getRequestFactory();
        factory.setConnectTimeout(3000);
        factory.setReadTimeout(3000);
        return template;
    }
	
    public static void main( String[] args )
    {
    	SpringApplication.run(SpringCloudZuulApplicationStart.class, args);
        System.out.println( "Zuul Started!" );
    }
    
    @RequestMapping("/")
    public String index(){
    	return "Welcome to Zuul";
    }
    
    @RequestMapping("/discovery")
    public String Discovery(){
    	String[] services = new String[]{"http://virtual/","http://host/"};
    	
    	int random = new Random().nextInt(2);
    	return client.getForObject(services[random], String.class);
    }
}
