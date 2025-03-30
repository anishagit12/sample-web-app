package com.fullcrudops.crudform.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullcrudops.crudform.Entity.CrudAppEntity;
import com.fullcrudops.crudform.Service.CrudAppServiceInterface;
import com.fullcrudops.crudform.Service.ElasticMailService;

import dto.CrudAppdto;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/entityObj")
public class CrudAppController {
	
	@Autowired
	CrudAppServiceInterface servObj;
	
	@Autowired
	private ElasticMailService emailService;
	
	//create user
	@PostMapping("/save")
	public String saveUser( @RequestBody CrudAppEntity entityObj) {
		String response = servObj.createUser(entityObj);
		emailService.sendConfirmEmail(entityObj.getEmail(), entityObj.getName());
		return response;
	}	
	
	//get all users
	@GetMapping("/getUsers")	
	public List<CrudAppEntity> getAllUsers(){
		return servObj.getUsers();
	}
	
	//get user by id
	@GetMapping("/getUser/{id}")
	public CrudAppEntity getUserId(@PathVariable long id) {
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
	
}
