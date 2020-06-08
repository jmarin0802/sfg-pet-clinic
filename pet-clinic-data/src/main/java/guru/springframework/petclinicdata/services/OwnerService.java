package guru.springframework.petclinicdata.services;

import java.util.List;

import guru.springframework.petclinicdata.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {  
	Owner findByLastName(String lastName);
	
	List<Owner> findAllByLastNameLike(String lastName);
}
