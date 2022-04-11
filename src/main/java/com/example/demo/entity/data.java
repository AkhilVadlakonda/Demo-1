package com.example.demo.entity;

import java.util.Date;

import lombok.Data;

@Data
public class data {
	private String id;
	private String username;
	private String about;
	private String submitted;
	private Date updated_at;
	private Integer submission_count;
	private Integer comment_count;
	private String created_at;

}
