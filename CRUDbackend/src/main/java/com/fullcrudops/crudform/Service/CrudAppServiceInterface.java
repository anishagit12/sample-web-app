package com.fullcrudops.crudform.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fullcrudops.crudform.Entity.CrudAppEntity;

import dto.CrudAppdto;

public interface CrudAppServiceInterface {
	
	//create user
	public String createUser(CrudAppEntity entityObj);
	
	//get all users
	public Page<CrudAppdto> getUsers(Pageable pageable);
	
	//get user by ID
	public CrudAppdto getUserById(long id);
	
	//update user
	public String updateUser(long id, CrudAppEntity entityObj);
	
	//delete user
	public String deleteUser(long id);
	
	//count number of users by state
	public long countUsersByState(String stateName);
	
	//get users by state
	public List<CrudAppdto> getUsersByState(String stateName);
	
}
