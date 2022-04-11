package com.example.demo.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpClientErrorException;

import com.example.demo.entity.ArticleUsersResponse;
import com.example.demo.entity.Product;
import com.example.demo.entity.data;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UsersRepository;
import com.example.demo.util.IdGenerator;
//import com.example.demo.util.RetryService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class ProductDao {

	@Autowired
	private ProductRepository repo;
	
	@Autowired
	private UsersRepository usersRepo;
	
	/*
	 * @Autowired private RetryService retryService;
	 */
	private Logger log = LoggerFactory.getLogger(ProductDao.class);

	public void saveProduct(Product product) {
		try {
			product.setId(IdGenerator.generateId());
			repo.insert(product);
		} catch (DataAccessException ex) {
			log.info("Exception is {}", ex);
			saveProduct(product);
		}
	}
	
	public void saveUsernames(List<data> list)
	{
		try {
			usersRepo.insert(list);
		} catch(DataAccessException e) {
			saveUsernames(list);
		}
		   
	}
	
	public Optional<Product> fetchProduct(String id) {
		return repo.findById(id);
	}

	public void deleteProduct(String id) {
		repo.deleteById(id);
	}
	
	public ArticleUsersResponse ActiveUsers(Integer page, HttpServletRequest request) throws JsonProcessingException {
		ArticleUsersResponse response1 =  new ArticleUsersResponse();
		/*
		 * ArticleUsersResponse response = new ArticleUsersResponse(); HttpHeaders
		 * headers = new HttpHeaders();
		 * headers.setContentType(MediaType.APPLICATION_JSON);
		 * headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); for(int
		 * i=1;i<=page;i++) { String url =
		 * "https://jsonmock.hackerrank.com/api/article_users?page=" + i; try {
		 * HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
		 * ResponseEntity<ArticleUsersResponse> responseEntity =
		 * retryService.hackerrankCall(url,requestEntity); response =
		 * responseEntity.getBody(); if(CollectionUtils.isEmpty(response1.getData())) {
		 * response1.setData(response.getData()); } else {
		 * response1.getData().addAll(response.getData()); }
		 * 
		 * } catch(HttpClientErrorException ex) { ex.printStackTrace(); } }
		 */
		return response1;
	}
}
