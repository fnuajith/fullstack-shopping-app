package com.company.shopping.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * Aspect to log around methods and log exceptions
 * Reference: https://docs.spring.io/spring-framework/docs/4.3.15.RELEASE/spring-framework-reference/html/aop.html#aop
 * 
 * @author Ajith
 *
 */
@Aspect
@Configuration
public class LoggingAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Pointcut that matches all the main pakages of the application
	 */
	@Pointcut("within(com.company.shopping.controller..*)" 
			+ " || within(com.company.shopping.service..*)"
			+ " || within(com.company.shopping.repository..*)")
	public void applicationPackagePointcut() {
	}

	/**
	 * Advice method to log on entry and exit to the methods'
	 * 
	 * @param joinPoint
	 * @return result
	 * @throws Throwable
	 */
	@Around("applicationPackagePointcut()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("Entered {}.{}() with argument(s) {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));

		Object result = joinPoint.proceed();

		logger.info("Exit {}.{}() with result {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), result);

		return result;
	}

	/**
	 * Advice that logs the methods that throw exceptions
	 * 
	 * @param joinPoint
	 * @param exception
	 */
	@AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "exception")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
		logger.error("Exception in {}.{} with cause {} and exception {}",
				joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
				exception.getCause() != null ? exception.getCause() : "NULL", exception.getMessage(), exception);
	}
}
