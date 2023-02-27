package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ExecutionTimeTracker {

	private static final Logger logger = LoggerFactory.getLogger(ExecutionTimeTracker.class);
	
	@Around("@annotation(com.example.demo.annotation.ExecutionTimer)")
	public Object trackExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
		
		String className = methodSignature.getDeclaringType().getSimpleName();
		String methodName = methodSignature.getName();
		
		StopWatch stopWatch = new StopWatch(className + "->" + methodName);
		stopWatch.start(methodName);
		Object result = proceedingJoinPoint.proceed();
		stopWatch.stop();
		
		logger.info("Execution of {}.{} took {} MS ",className,methodName, stopWatch.getTotalTimeMillis());
		
		return result;
	}
}
