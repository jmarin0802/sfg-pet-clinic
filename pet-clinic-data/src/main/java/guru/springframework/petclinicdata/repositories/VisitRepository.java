package guru.springframework.petclinicdata.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.petclinicdata.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long>{

}
