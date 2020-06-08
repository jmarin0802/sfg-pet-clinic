package guru.springframework.petclinicdata.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.petclinicdata.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long>{

	Owner findByLastName(String lastName);
	
	List<Owner> findAllByLastNameLike(String lastName);
}
