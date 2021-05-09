package com.company.shopping.controller;

import org.springframework.http.HttpHeaders;

/**
 * A utility class with method(s) to create HttpHeaders with custom messages
 * 
 * @author Ajith
 *
 */
public final class HeaderUtil {
	
	/**
	 * Creates and returns a custom header 'X-Shopping-App-Error-Message' populated with the provided message
	 * 
	 * @param errorMessage
	 * @return headers
	 */
	public static HttpHeaders createErrorHeader(String errorMessage) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-Shopping-App-Error-Message", errorMessage);
		return headers;
	}

}
