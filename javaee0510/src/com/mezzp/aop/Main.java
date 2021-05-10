package com.mezzp.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
public static void main(String[] args) {
	ApplicationContext cxt = new ClassPathXmlApplicationContext("spring-aop.xml");
	
	ArithmeticCalculator arithmeticCalculator=cxt.getBean("arithmeticCalculatorImpl",ArithmeticCalculator.class);
	System.out.println(arithmeticCalculator);
	int result = arithmeticCalculator.add(10, 20);
	System.out.println("The result"+result);
}
}
