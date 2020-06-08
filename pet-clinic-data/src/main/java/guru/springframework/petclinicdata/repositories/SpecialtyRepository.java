package guru.springframework.petclinicdata.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.petclinicdata.model.Speciality;

public interface SpecialtyRepository extends CrudRepository<Speciality, Long>{

}
