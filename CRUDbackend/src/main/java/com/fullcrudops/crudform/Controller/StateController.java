package com.fullcrudops.crudform.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullcrudops.crudform.Entity.State;
import com.fullcrudops.crudform.Service.StateServiceInterface;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/StateOps")
@AllArgsConstructor
public class StateController {
	
	@Autowired
	StateServiceInterface stateServObj;
	
	@PostMapping("/save")
	public ResponseEntity<String> createState( @RequestBody State stateEntObj) {
		
		try {
			stateServObj.createState(stateEntObj);
			return ResponseEntity.ok("State '"+ stateEntObj.getName() +"' saved sucessfully.");
		}
		catch(DataIntegrityViolationException e) {
			System.out.println("Error: State with name '"+stateEntObj.getName()+"' already exists.");
			return ResponseEntity.badRequest().body("State '"+stateEntObj.getName()+"' already exists.");
			
		}
	}
	
	@GetMapping("/getAllStates")
	public List<State> getAllStates(){
		return stateServObj.getAllStatesSorted();
	}

}
