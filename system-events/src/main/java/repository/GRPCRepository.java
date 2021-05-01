package repository;
import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import model.GRPCModel;


@Repository
public class GRPCRepository extends SimpleJpaRepository<GRPCModel, Long> {
	private EntityManager entityManager;
	
	public GRPCRepository(EntityManager entityManager) {
		super(GRPCModel.class, entityManager);
		this.entityManager = entityManager;
	}
	
}



