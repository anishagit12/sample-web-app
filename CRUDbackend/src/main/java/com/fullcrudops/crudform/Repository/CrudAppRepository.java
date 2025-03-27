package com.fullcrudops.crudform.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.fullcrudops.crudform.Entity.CrudAppEntity;
import com.fullcrudops.crudform.Entity.State;

@Repository
public interface CrudAppRepository extends JpaRepository<CrudAppEntity, Long>, JpaSpecificationExecutor<CrudAppEntity>{
	CrudAppEntity findByEmail(String email);
	long countByState(State state);
	List<CrudAppEntity> findByState(State state);
}
