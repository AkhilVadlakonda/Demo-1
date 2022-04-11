package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/product/{id}")
	public ResponseEntity<Product> fetchProduct(@PathVariable String id) {
		Product product = productService.fetchProduct(id);
		ResponseEntity<Product> resp = new ResponseEntity<Product>(product, HttpStatus.OK);
		return resp;
	}

	@PostMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		String id = productService.saveProduct(product);
		ResponseEntity<String> resp = new ResponseEntity<String>(id, HttpStatus.OK);
		return resp;
	}

	@DeleteMapping(value = "/product/{id}")
	public void deleteProduct(@PathVariable String id) {
		productService.deleteProduct(id);

	}
	
	@RequestMapping(value = "/article_users/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public DeferredResult<ResponseEntity<List<String>>> getUsers(@PathVariable Integer page, HttpServletRequest request) throws JsonProcessingException {
		DeferredResult<ResponseEntity<List<String>>> defferdresult = new DeferredResult<>();
		List<String> count  = new ArrayList<>(productService.ActiveUsers(page, request));
		defferdresult.setResult(new ResponseEntity<>(count, HttpStatus.OK));
		return defferdresult;
	}

}
