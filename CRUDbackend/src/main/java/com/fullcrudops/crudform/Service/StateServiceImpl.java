package com.fullcrudops.crudform.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcrudops.crudform.Entity.State;
import com.fullcrudops.crudform.Repository.StateRepository;

@Service
public class StateServiceImpl implements StateServiceInterface{
	
	@Autowired
	StateRepository stateRepoObj;
	
	public State createState(State stateEntObj) {
		return stateRepoObj.save(stateEntObj);
	}
	
	public List<State> getAllStates(){
		return stateRepoObj.findAll();
	}

}
