package com.mezzp.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	@Before("execution(public int com.mezzp.aop.ArithmeticCalculator.*(int ,int))")
	public  void beforeMethod(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().getName();
		Object[] args = joinpoint.getArgs();
		System.out.println("INFO:The method "+methodName+" begin with"+Arrays.asList(args));
	}

}
