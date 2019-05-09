package com.mt.member.api.controller;

import java.util.Date;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mt.member.api.db.entity.TbUser;
import com.mt.member.api.db.repository.TbUserRepository;
import com.mt.member.api.model.AuthAddRequestModel;
import com.mt.member.api.model.AuthAddResponseModel;
import com.mt.member.api.model.UserAddRequestModel;
import com.mt.member.api.model.UserAddResponseModel;
import com.mt.member.api.util.MD5;

@RestController
@RequestMapping("/user")
public class UserController {

	private Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TbUserRepository tbUserRepository;
	
	@PostMapping("/add")
	@Transactional
	public HttpEntity<?> postAdd(@Valid @RequestBody UserAddRequestModel requestModel) throws Exception {
		requestModel.setTbuPassword(MD5.getInstance().get(requestModel.getTbuPassword()));
		
		UserAddResponseModel responseModel = new UserAddResponseModel(requestModel);
		ResponseEntity<?> responseEntity = null;
		
		TbUser exampleTbUser = new TbUser();
		exampleTbUser.setTbuEmail(requestModel.getTbuEmail());
		
		if (tbUserRepository.count(Example.of(exampleTbUser)) > 0) {
			responseModel.setStatus("208");
			responseModel.setError("Email already exists");

			responseEntity = new ResponseEntity<>(responseModel, HttpStatus.ALREADY_REPORTED);
			log.info("postAdd responseEntity : " + objectMapper.writeValueAsString(responseEntity));

			return responseEntity;
		} else {
			TbUser tbUser = modelMapper.map(requestModel, TbUser.class);
			tbUser.setTbuCreateDate(new Date());
			tbUser.setTbuCreateId(0);
			tbUserRepository.save(tbUser);
			
			AuthAddRequestModel authAddRequestModel = new AuthAddRequestModel();
			authAddRequestModel.setRequestDate(requestModel.getRequestDate());
			authAddRequestModel.setRequestId(requestModel.getRequestId());
			authAddRequestModel.setTbaEmail(requestModel.getTbuEmail());
			authAddRequestModel.setTbaPassword(requestModel.getTbuPassword());
			
			HttpEntity<AuthAddRequestModel> request = new HttpEntity<>(authAddRequestModel);
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.postForEntity("http://localhost:8082/auth/add", request, String.class);
			
			responseModel.setTbUsers(tbUser);
			responseModel.setStatus("200");

			responseEntity = new ResponseEntity<>(responseModel, HttpStatus.OK);
			log.info("postAdd responseEntity : " + objectMapper.writeValueAsString(responseEntity));

			return responseEntity;
		}
	}
}
