package repository;
import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.GRPCModel;


@Repository
public interface GRPCRepository extends CrudRepository<GRPCModel, Long> { 
	
}



