package com.productms.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "empty"})
public class Product {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private String description;
	private Double price;

	
	public Boolean isEmpty() {
		
		if (this.id == null &&
			this.name == null &&
			this.description == null &&
			this.price == null) {
			return true;
		}
		
		return false;
	}
	
}
