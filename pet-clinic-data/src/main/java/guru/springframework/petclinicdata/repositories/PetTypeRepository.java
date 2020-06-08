package guru.springframework.petclinicdata.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.petclinicdata.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long>{

}
