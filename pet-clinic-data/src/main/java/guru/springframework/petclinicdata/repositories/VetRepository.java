package guru.springframework.petclinicdata.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.petclinicdata.model.Vet;

public interface VetRepository extends CrudRepository<Vet, Long>{

}
