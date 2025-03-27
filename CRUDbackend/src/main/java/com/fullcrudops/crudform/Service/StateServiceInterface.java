package com.fullcrudops.crudform.Service;

import java.util.List;

import com.fullcrudops.crudform.Entity.State;


public interface StateServiceInterface {
	
	public State createState(State stateEntObj);
	public List<State> getAllStates();

}
