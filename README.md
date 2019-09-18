# spring-rmi

## Connect Between Java Spring projects via RMI 

This is an example of how to set-up an [Spring](https://spring.io)
projects to Connect to Other Java project via RMI protocl.
and The Code on the repo : [spring-rmi](https://https://github.com/engahmedragab/spring-rmi) 


## Create a maven projects 

1. spring-rmi-client: as the client project used as the source of the implemntations of classes.
2. spring-rmi-server: as the server project used to call the bean of the classes via rmi.



## spring-rmi-client projects 

```
com
├──  SpringRmiServerApplication.java(Main)
└── service
    ├── HelloWorldRMI.java [inteface]
    └── HelloWorldRMIimpl.java [impl]
```
>here we create the interface of the bean object and the impl clasess we want to call from the client .

- in `main` func we add 
```java
	System.setProperty("java.rmi.server.hostname","192.168.113.29");
	new ClassPathXmlApplicationContext("applicationContext.xml");
```

- `applicationContext.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
	xmlns:aop="http://www.springframework.org/schema/aop" xmlns:context="http://www.springframework.org/schema/context"
	xmlns:jee="http://www.springframework.org/schema/jee" xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:task="http://www.springframework.org/schema/task"
	xsi:schemaLocation="http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.2.xsd http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd http://www.springframework.org/schema/jee http://www.springframework.org/schema/jee/spring-jee-3.2.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.2.xsd http://www.springframework.org/schema/task http://www.springframework.org/schema/task/spring-task-3.2.xsd">
 
	<context:component-scan base-package="com.examples" />

	
	<bean id="helloWorldRMIservice" class="com.example.springrmiserver.service.HelloWorldRMIimpl">
    	<!-- any additional properties, maybe a DAO? -->
	</bean>
	
	<bean class="org.springframework.remoting.rmi.RmiServiceExporter">
	<!-- does not necessarily have to be the same name as the bean to be exported -->
		<property name="serviceName" value="helloWorldRMIservice"/>
		<property name="service" ref="helloWorldRMIservice"/>
		<property name="serviceInterface" value="com.example.springrmiserver.service.HelloWorldRMI"/>
		<!-- defaults to 1099 -->
		<property name="registryPort" value="1099"/>
	</bean>
</beans>
```

## spring-rmi-client projects 

```
com
├──  springrmiclient
    └── SpringRmiClientApplication.java (Main)
└── springrmiserver
    └── HelloWorldRMI.java [inteface]
```

>here we create only the interface of the bean object we want to call as ( "`HelloWorldRMI.java`" ) .

- in `main` func we add 

```java
    	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    	HelloWorldRMI obj = (HelloWorldRMI) context.getBean("helloWorldRMIservice");   
		System.out.println("================Client Side ========================");
		System.out.println(obj.sayHelloRmi("Ragab"));

```
- `applicationContext.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
	xmlns:aop="http://www.springframework.org/schema/aop" xmlns:context="http://www.springframework.org/schema/context"
	xmlns:jee="http://www.springframework.org/schema/jee" xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:task="http://www.springframework.org/schema/task"
	xsi:schemaLocation="http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.2.xsd http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd http://www.springframework.org/schema/jee http://www.springframework.org/schema/jee/spring-jee-3.2.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.2.xsd http://www.springframework.org/schema/task http://www.springframework.org/schema/task/spring-task-3.2.xsd">
 
	<context:component-scan base-package="com.examples" />
	
	<bean id="helloWorldRMIservice" class="org.springframework.remoting.rmi.RmiProxyFactoryBean">
		<property name="serviceUrl" value="rmi://localhost:1099/helloWorldRMIservice"/>
		<property name="serviceInterface" value="com.example.springrmiserver.service.HelloWorldRMI"/>
		<property name="refreshStubOnConnectFailure" value="true"/>
	</bean>	
</beans>
```


> open the two projects on eclipce run and ....