package guru.springframework.petclinicdata.services.map.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.petclinicdata.model.Vet;
import guru.springframework.petclinicdata.repositories.VetRepository;
import guru.springframework.petclinicdata.services.VetService;

@Service
@Profile("springdatajpa")
public class VetSDJpaService implements VetService{

	private final VetRepository vetRepository;
	
	
	public VetSDJpaService(VetRepository vetRepository) {
		super();
		this.vetRepository = vetRepository;
	}

	@Override
	public Set<Vet> findAll() {
		Set<Vet> vets = new HashSet<>();
		vetRepository.findAll().forEach(vets::add);;
		return vets;
	}

	@Override
	public Vet findById(Long id) {
		return vetRepository.findById(id).orElse(null);
	}

	@Override
	public Vet save(Vet object) {
		return vetRepository.save(object);
	}

	@Override
	public void delete(Vet object) {
		vetRepository.delete(object);
	}

	@Override
	public void deleById(Long id) {
		vetRepository.deleteById(id);
	}

}
