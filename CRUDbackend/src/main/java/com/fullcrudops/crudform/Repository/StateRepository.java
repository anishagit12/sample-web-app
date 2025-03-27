package com.fullcrudops.crudform.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullcrudops.crudform.Entity.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{
	Optional<State> findByName(String name);
}
