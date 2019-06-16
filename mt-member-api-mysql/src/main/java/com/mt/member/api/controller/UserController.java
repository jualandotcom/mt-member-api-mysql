package com.mt.member.api.controller;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mt.member.api.db.entity.TbUser;
import com.mt.member.api.db.repository.TbUserRepository;
import com.mt.member.api.model.AuthAddRequestModel;
import com.mt.member.api.model.UserAddRequestModel;
import com.mt.member.api.model.UserAddResponseModel;
import com.mt.member.api.model.UserConfirmationRequestModel;
import com.mt.member.api.model.UserConfirmationResponseModel;
import com.mt.member.api.model.UserNotifyRequestModel;
import com.mt.member.api.model.UserNotifyResponseModel;
import com.mt.member.api.util.MD5;
import com.mt.member.api.util.Uid;

@CrossOrigin
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
	
	@Autowired
    public JavaMailSender javaMailSender;
	
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
			tbUser.setTbuStatus(TbUser.statusCreated);
			tbUser.setTbuUid(Uid.getInstance().generateString(100));
			tbUserRepository.save(tbUser);
			
			responseModel.setTbUsers(tbUser);
			responseModel.setStatus("200");

			responseEntity = new ResponseEntity<>(responseModel, HttpStatus.OK);
			log.info("postAdd responseEntity : " + objectMapper.writeValueAsString(responseEntity));

			return responseEntity;
		}
	}
	
	@PostMapping("/notify")
	@Transactional
	public HttpEntity<?> postNotify(@Valid @RequestBody UserNotifyRequestModel requestModel) throws Exception {
		UserNotifyResponseModel responseModel = new UserNotifyResponseModel(requestModel);
		ResponseEntity<?> responseEntity = null;
		
		TbUser exampleTbUser = new TbUser();
		exampleTbUser.setTbuUid(requestModel.getTbuUid());
		exampleTbUser.setTbuStatus(TbUser.statusCreated);
		
		Optional<TbUser> optTbUser = tbUserRepository.findOne(Example.of(exampleTbUser));
		
		if (tbUserRepository.count(Example.of(exampleTbUser)) > 0) {
			optTbUser.get().setTbuUpdateDate(new Date());
			optTbUser.get().setTbuUpdateId(0);
			optTbUser.get().setTbuStatus(TbUser.statusNeedConfirmation);
			
			SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo("achmad.amri@alfadigital.id");
	        message.setSubject("confirmation");
	        message.setText("you need to confirm your account with this link http://mintol.com/confirmation.html");
	        javaMailSender.send(message);
			
			responseModel.setTbUsers(optTbUser.get());
			responseModel.setStatus("200");
			
			responseEntity = new ResponseEntity<>(responseModel, HttpStatus.OK);
			log.info("postNotify responseEntity : " + objectMapper.writeValueAsString(responseEntity));

			return responseEntity;
		} else {
			responseModel.setStatus("401");
			responseModel.setError("Data not found or already notified");
			
			responseEntity = new ResponseEntity<>(responseModel, HttpStatus.UNAUTHORIZED);
			log.info("postNotify responseEntity : " + objectMapper.writeValueAsString(responseEntity));

			return responseEntity;
		}
	}
	
	@PostMapping("/confirmation")
	@Transactional
	public HttpEntity<?> postConfirmation(@Valid @RequestBody UserConfirmationRequestModel requestModel) throws Exception {
		UserConfirmationResponseModel responseModel = new UserConfirmationResponseModel(requestModel);
		ResponseEntity<?> responseEntity = null;
		
		TbUser exampleTbUser = new TbUser();
		exampleTbUser.setTbuUid(requestModel.getTbuUid());
		exampleTbUser.setTbuStatus(TbUser.statusNeedConfirmation);
		
		Optional<TbUser> optTbUser = tbUserRepository.findOne(Example.of(exampleTbUser));
		
		if (tbUserRepository.count(Example.of(exampleTbUser)) > 0) {
			optTbUser.get().setTbuUpdateDate(new Date());
			optTbUser.get().setTbuUpdateId(0);
			optTbUser.get().setTbuStatus(TbUser.statusActive);
			
			AuthAddRequestModel authAddRequestModel = new AuthAddRequestModel();
			authAddRequestModel.setRequestDate(requestModel.getRequestDate());
			authAddRequestModel.setRequestId(requestModel.getRequestId());
			authAddRequestModel.setTbaEmail(optTbUser.get().getTbuEmail());
			authAddRequestModel.setTbaPassword(optTbUser.get().getTbuPassword());
			
			HttpEntity<AuthAddRequestModel> request = new HttpEntity<>(authAddRequestModel);
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.postForEntity("http://localhost:8082/auth/add", request, String.class);
			
			responseModel.setTbUsers(optTbUser.get());
			responseModel.setStatus("200");
			
			responseEntity = new ResponseEntity<>(responseModel, HttpStatus.OK);
			log.info("postConfirmation responseEntity : " + objectMapper.writeValueAsString(responseEntity));

			return responseEntity;
		} else {
			responseModel.setStatus("401");
			responseModel.setError("Data not found or already confirmed");
			
			responseEntity = new ResponseEntity<>(responseModel, HttpStatus.UNAUTHORIZED);
			log.info("postConfirmation responseEntity : " + objectMapper.writeValueAsString(responseEntity));

			return responseEntity;
		}
	}
	
	@GetMapping("/test")
	@Transactional
	public HttpEntity<?> postTest() {
//		UserConfirmationResponseModel responseModel = new UserConfirmationResponseModel(new UserConfirmationRequestModel());
//		
//		AuthCheckRequestModel authCheckRequestModel = new AuthCheckRequestModel();
//		authCheckRequestModel.setRequestDate("2019-03-21T16:56:50.706");
//		authCheckRequestModel.setRequestId("0123456789");
//		authCheckRequestModel.setTbaEmail("achmad.amri@gmail.com");
//		authCheckRequestModel.setTbaToken("eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJtdC1hdXRoLWFwaSIsInN1YiI6InRva2VuIiwibmFtZSI6ImFjaG1hZC5hbXJpQGdtYWlsLmNvbSIsInNjb3BlIjoiYWRtaW5zIiwiaWF0IjoxNTU4MzM5Mzk2LCJleHAiOjE1NTg0MjU3OTZ9.TrcRAG78Bkxu7R9MAagqkb5jwrU22gDFXdaPNayBlBU");
//		
//		if (Auth.getInstance().check(authCheckRequestModel)) {
//			return new ResponseEntity<>(responseModel, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
		UserAddRequestModel x = new UserAddRequestModel();
		return new ResponseEntity<>(x, HttpStatus.OK);
	}
}
