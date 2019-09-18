package com.example.springrmiclient;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import com.example.springrmiclient.SimpleObjectRmi;
import com.example.springrmiserver.service.HelloWorldRMI;

@SpringBootApplication
public class SpringRmiClientApplication {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    	HelloWorldRMI obj = (HelloWorldRMI) context.getBean("helloWorldRMIservice");   
		System.out.println("================Client Side ========================");
		System.out.println(obj.sayHelloRmi("Ragab"));
	}



	
}
