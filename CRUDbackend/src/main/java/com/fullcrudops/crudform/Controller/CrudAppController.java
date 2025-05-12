package com.fullcrudops.crudform.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fullcrudops.crudform.Entity.CrudAppEntity;
import com.fullcrudops.crudform.Service.CrudAppServiceInterface;
import com.fullcrudops.crudform.Service.ElasticMailService;
import com.fullcrudops.crudform.Service.UserAnalyticsClientService;

import dto.CrudAppdto;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/entityObj")
public class CrudAppController {
	
	@Autowired
	CrudAppServiceInterface servObj;
	
	@Autowired
	private ElasticMailService emailService;
	
	@Autowired
	private final UserAnalyticsClientService analyticsServObj;
	
	public CrudAppController(UserAnalyticsClientService analyticsServObj) {
		this.analyticsServObj = analyticsServObj;
	}
	
	//create user
	@PostMapping("/save")
	public String saveUser( @RequestBody CrudAppEntity entityObj) {
		String response = servObj.createUser(entityObj);
		emailService.sendConfirmEmail(entityObj.getEmail(), entityObj.getName());
		return response;
	}	
	
	//get all users
	@GetMapping("/getUsers")	
	public Page<CrudAppdto> getAllUsers( @RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="15") int size){
		Pageable pageable = PageRequest.of(page, size);
		return servObj.getUsers(pageable);
	}
	
	//get user by id
	@GetMapping("/getUser/{id}")
	public CrudAppdto getUserId(@PathVariable long id) {
		return servObj.getUserById(id);
	}
	
	//update existing users
	@PutMapping("updateUser/{id}")
	public String updateUser(@PathVariable long id,
			@RequestBody CrudAppEntity entityObj) {
		return servObj.updateUser(id, entityObj);
	}
	
	//delete existing users
	@DeleteMapping("deleteUser/{id}")
	public String deleteUser(@PathVariable long id) {
		return servObj.deleteUser(id);
	}
	
	//count users by state
	@PostMapping("/countUsersByState")
	public ResponseEntity<String> countUsersByState(@RequestBody CrudAppEntity entityObj){
		String stateName = entityObj.getStateName();
		long count = servObj.countUsersByState(stateName);
		return ResponseEntity.ok("Number of users from "+stateName+": "+count);
	}
	
	//get users by state
	@PostMapping("/getUsersByState")
	public ResponseEntity<List<CrudAppdto>> getUsersByState( @RequestBody CrudAppdto dtoObj){
		
		return ResponseEntity.ok(servObj.getUsersByState(dtoObj.getStateName()));
	}
	
	//record login activity in UserAnalytics microservice
	@PostMapping("/login")
	public String loginUser(@RequestParam String email) {
		analyticsServObj.logUserActivity(email, "LOGIN");
		return "User logged in!";
	}
	
	//get login activity from UserAnalytics microservice
	@GetMapping("/activity/{email}")
	public Mono<String> getUserActivity(@PathVariable String email) {
		
		return analyticsServObj.getUserActivity(email);
	}	
}
