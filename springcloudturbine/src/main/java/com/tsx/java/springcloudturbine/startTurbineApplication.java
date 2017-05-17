package com.tsx.java.springcloudturbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.Bean;

import com.netflix.turbine.discovery.ConfigPropertyBasedDiscovery;
import com.netflix.turbine.discovery.InstanceDiscovery;

/**
 * Hello world!
 *@author taoshuangxi
 */
@SpringBootApplication
@EnableTurbine
public class startTurbineApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(startTurbineApplication.class, args);
        System.out.println( "Turbine start up!" );
    }
    
    @Bean
	public InstanceDiscovery instanceDiscovery() {
		return new ConfigPropertyBasedDiscovery();
	}
}
