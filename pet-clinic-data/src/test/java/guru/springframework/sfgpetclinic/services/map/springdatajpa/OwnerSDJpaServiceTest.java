package guru.springframework.sfgpetclinic.services.map.springdatajpa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;

/*
 *Created for jalemaov on 17-05-2020
 */
@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
	@Mock
	OwnerRepository ownerRepository;
	@Mock
	PetTypeRepository petTypeRepository;
	@Mock
	PetRepository petRepository;
	@InjectMocks
	OwnerSDJpaService ownerSDJpaService;

	Owner returnOwner;

	@BeforeEach
	void setUp() throws Exception {
		//MockitoAnnotations.initMocks(this);
		//ownerSDJpaService =new OwnerSDJpaService(ownerRepository, petTypeRepository, petRepository);
		returnOwner = Owner.builder().id(1L).lastName("smith").build();
	}

	@Test
	void testFindAll() {
		Set<Owner> owners = new HashSet<>();
		owners.add(Owner.builder().id(1L).lastName("smith").build());
		owners.add(Owner.builder().id(2L).build());
		owners.add(Owner.builder().id(3L).build());
		when(ownerRepository.findAll()).thenReturn(owners);
		Set<Owner> owners2 = new HashSet<>();
		owners2 = ownerSDJpaService.findAll();
		assertNotNull(owners2);
		assertEquals(3, owners2.size());
		//verify(ownerSDJpaService, times(1)).findAll();
		
	}

	@Test
	void testFindById() {
		when(ownerRepository.findById(any())).thenReturn(Optional.of(returnOwner));
		
		Owner owner = ownerSDJpaService.findById(1L);
		
		assertNotNull(owner);
	}

	@Test
	void testSave() {
		Owner ownerToSave = Owner.builder().id(1L).build();
		when(ownerRepository.save(any())).thenReturn(returnOwner);
		Owner savedOwner = ownerSDJpaService.save(ownerToSave);
		assertNotNull(savedOwner);
	}

	@Test
	void testDelete() {
		ownerSDJpaService.delete(returnOwner);
		verify(ownerRepository, times(1)).delete(any());
		
	}

	@Test
	void testDeleById() {
		ownerSDJpaService.deleById(1L);
		verify(ownerRepository, times(1)).deleteById(anyLong());
	}

	@Test
	void testFindByLastName() {
		when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);
		Owner owner = ownerSDJpaService.findByLastName("smith");
		assertEquals("smith", owner.getLastName());
		verify(ownerRepository).findByLastName(any());
	}

}
