package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dao.ProductDao;
import com.example.demo.entity.ArticleUsersResponse;
import com.example.demo.entity.Product;
import com.example.demo.entity.data;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;

	private Logger log = LoggerFactory.getLogger(ProductService.class);

	public Product fetchProduct(String id) {
		Optional<Product> product = productDao.fetchProduct(id);
		if (!product.isPresent()) {
			log.info("Product Not Exists");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not exists");
		}
		return product.get();
	}

	public String saveProduct(Product product) {
		log.info("Product {}", product);
		product.setLastModifiedDate(new Date());
		productDao.saveProduct(product);
		return product.getId();
	}

	public void deleteProduct(String id) {

			productDao.deleteProduct(id);
			}
	

	public  List<String> ActiveUsers(Integer page,HttpServletRequest request) throws JsonProcessingException {
		// TODO Auto-generated method stub
		ArticleUsersResponse response = productDao.ActiveUsers(page, request);
		List<data> s  = response.getData().stream().filter(x -> x.getSubmission_count()>10).collect(Collectors.toList());
		List<String> res = new ArrayList<>();
		for(data d: s)
			res.add(d.getUsername());
		productDao.saveUsernames(response.getData());
		return res;
	}
}
