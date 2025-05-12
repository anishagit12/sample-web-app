package com.fullcrudops.crudform.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fullcrudops.crudform.Entity.CrudAppEntity;
import com.fullcrudops.crudform.Entity.State;
import com.fullcrudops.crudform.Repository.CrudAppRepository;
import com.fullcrudops.crudform.Repository.StateRepository;

import dto.CrudAppdto;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CrudAppServiceImpl implements CrudAppServiceInterface {
	
	
	CrudAppRepository reposObj;
	
	StateRepository stateRepoObj;
	
	//Create method
	public String createUser(CrudAppEntity entityObj) {
		
		CrudAppEntity existingUser = reposObj.findByEmail(entityObj.getEmail());
		if(Objects.nonNull(existingUser)) {
			return "User already exists";
		}
		else {
			State state = stateRepoObj.findByName(entityObj.getStateName()).orElseThrow(()
									-> new RuntimeException("State does not exist."));
			entityObj.setState(state);
			reposObj.save(entityObj);
			return "User saved successfully";
		}
	}
	
	//Read ALL users
	public Page<CrudAppdto> getUsers(Pageable pageable){
		Page<CrudAppEntity> usersPage = reposObj.findAll(pageable);
		return usersPage.map(CrudAppdto::new);
	}
	
	//read user by id
	public CrudAppdto getUserById(long id) {
		CrudAppEntity userEntity = reposObj.findById(id)
										   .orElseThrow(()-> new RuntimeException("User not found"));
		CrudAppdto dto = new CrudAppdto();
		dto.setId(userEntity.getId());
		dto.setName(userEntity.getName());
		dto.setEmail(userEntity.getEmail());
		dto.setStateName(userEntity.getState().getName());
		
		return dto;
	}
	
	//update user
	public String updateUser(long id, CrudAppEntity entityObj) {
		
		CrudAppEntity existingUser = reposObj.findById(id).orElseThrow(()-> new RuntimeException("User not found."));
		
		if(entityObj.getName()!=null) {
			existingUser.setName(entityObj.getName());
		}
		
		if(entityObj.getEmail()!=null) {
			existingUser.setEmail(entityObj.getEmail());
		}
		
		if(entityObj.getStateName() != null && !entityObj.getStateName().isEmpty() ) {
			Optional<State> state = stateRepoObj.findByName(entityObj.getStateName());
			if(state.isPresent()) {
				existingUser.setState(state.get());
			}
			else {
				return "Enter a valid state name.";
			}
		}
		
		reposObj.save(existingUser);
		return "User updated successfully.";
		
	}
	
	//delete user
	public String deleteUser(long id) {
		CrudAppEntity existingUser = reposObj.findById(id).orElse(null);
		if(existingUser == null) {
			return "User does not exist.";
		}
		else {
			reposObj.delete(existingUser);
			return "User deleted successfully.";
		}
	}
	
	//count users by state
	public long countUsersByState(String stateName) {
		
		State state = stateRepoObj.findByName(stateName).orElseThrow(()-> new RuntimeException("Enter a valid state name."));
		return reposObj.countByState(state);		
	}
	
	//get users by state
	public List<CrudAppdto> getUsersByState(String stateName){
		
		State state = stateRepoObj.findByName(stateName)
				.orElseThrow(()-> new RuntimeException("Enter a valid state name."));
		
		List<CrudAppEntity> users = reposObj.findAll((root, query, cb)-> 
		cb.equal(root.get("state").get("id"), state.getId()));
		
		return users.stream().map(user -> {
			CrudAppdto dtoObj = new CrudAppdto();
			dtoObj.setId(user.getId());
			dtoObj.setName(user.getName());
			dtoObj.setEmail(user.getEmail());
			dtoObj.setStateName(user.getState().getName());
			return dtoObj;
		}).collect(Collectors.toList());
		
	}

}
