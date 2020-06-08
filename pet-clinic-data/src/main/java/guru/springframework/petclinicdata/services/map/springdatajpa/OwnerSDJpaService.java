package guru.springframework.petclinicdata.services.map.springdatajpa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.petclinicdata.model.Owner;
import guru.springframework.petclinicdata.repositories.OwnerRepository;
import guru.springframework.petclinicdata.repositories.PetRepository;
import guru.springframework.petclinicdata.repositories.PetTypeRepository;
import guru.springframework.petclinicdata.services.OwnerService;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {

	private final OwnerRepository ownerRepository;
	private final PetTypeRepository petTypeRepository;
	private final PetRepository petRepository;
	
	public OwnerSDJpaService(OwnerRepository ownerRepository, PetTypeRepository petTypeRepository,
			PetRepository petRepository) {
		this.ownerRepository = ownerRepository;
		this.petTypeRepository = petTypeRepository;
		this.petRepository = petRepository;
	}

	@Override
	public Set<Owner> findAll() {
		Set<Owner> owners = new HashSet<>();
		ownerRepository.findAll().forEach(owners::add);
		return owners;
	}

	@Override
	public Owner findById(Long id) {
		return ownerRepository.findById(id).orElse(null);
	}

	@Override
	public Owner save(Owner object) {
		return ownerRepository.save(object);
	}

	@Override
	public void delete(Owner object) {
		ownerRepository.delete(object);
	}

	@Override
	public void deleById(Long id) {
		ownerRepository.deleteById(id);
	}

	@Override
	public Owner findByLastName(String lastName) {
		// TODO Auto-generated method stub
		return ownerRepository.findByLastName(lastName);
	}

	@Override
	public List<Owner> findAllByLastNameLike(String lastName) {
		return null;
	}

}
