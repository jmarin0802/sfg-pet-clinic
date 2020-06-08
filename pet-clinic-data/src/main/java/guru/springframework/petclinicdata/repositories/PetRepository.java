package guru.springframework.petclinicdata.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.petclinicdata.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long>{

}
