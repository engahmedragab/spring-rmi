package com.example.springrmiserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringRmiServerApplication {

	public static void main(String[] args) {
		System.setProperty("java.rmi.server.hostname","192.168.113.29");
		new ClassPathXmlApplicationContext("applicationContext.xml");
	}
}
