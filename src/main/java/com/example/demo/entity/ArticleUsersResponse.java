package com.example.demo.entity;

import java.util.List;

import lombok.Data;


@Data
public class ArticleUsersResponse {
	private String page;
	private String per_page;
	private String total;
	private String total_pages;
	private List<data> data;

}
