package com.mt.member.api.controller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mt.member.api.db.entity.TbUser;
import com.mt.member.api.db.repository.TbUserRepository;
import com.mt.member.api.model.UserAddRequestModel;
import com.mt.member.api.model.UserAddResponseModel;

@RestController
@RequestMapping("/test")
public class TestController {

	private Logger log = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TbUserRepository tbUserRepository;
	
	@GetMapping("/test")
	public HttpEntity<?> test() throws Exception {
		UserAddRequestModel requestModel = new UserAddRequestModel();
		UserAddResponseModel responseModel = new UserAddResponseModel(requestModel);
		
		TbUser tbUser = tbUserRepository.findAll().get(0);
		
		responseModel.setTbUsers(tbUser);
		responseModel.setStatus("200");
		
		ResponseEntity<?> responseEntity = new ResponseEntity<>(responseModel, HttpStatus.OK);
		
		return responseEntity;
	}
}
