package com.example.demo.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "product")
public class Product {
	@Id
	private String id;
	private int price;
	private String title;
	
	@LastModifiedDate
	private Date lastModifiedDate;
}
