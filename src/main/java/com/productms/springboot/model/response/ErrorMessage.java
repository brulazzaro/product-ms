package com.productms.springboot.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {

	private int status_code;
	private String message;
	
}
