package com.fullcrudops.crudform.Service;

import java.util.List;

import com.fullcrudops.crudform.Entity.CrudAppEntity;

import dto.CrudAppdto;

public interface CrudAppServiceInterface {
	
	//create user
	public String createUser(CrudAppEntity entityObj);
	
	//get all users
	public List<CrudAppdto> getUsers();
	
	//get user by ID
	public CrudAppEntity getUserById(long id);
	
	//update user
	public String updateUser(long id, CrudAppEntity entityObj);
	
	//delete user
	public String deleteUser(long id);
	
	//count number of users by state
	public long countUsersByState(String stateName);
	
	//get users by state
	public List<CrudAppdto> getUsersByState(String stateName);
	
}
