package guru.springframework.sfgpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.petclinicdata.model.Owner;
import guru.springframework.petclinicdata.repositories.OwnerRepository;
import guru.springframework.petclinicdata.services.PetService;
import guru.springframework.petclinicdata.services.PetTypeService;
import guru.springframework.petclinicdata.services.map.OwnerMapService;

/*
 *Created for jalemaov on 15-05-2020
 */
class OwnerMapServiceTest {

	
	OwnerMapService ownerMapService;
	final Long ownerId=1L;
	final String firstName="javier";
	final String lastName="Marin";
	
	@BeforeEach
	void setUp() throws Exception {
		ownerMapService = new OwnerMapService(null, null, null);
		ownerMapService.save(Owner.builder().id(ownerId).firstName(firstName).lastName(lastName).build());
	}
	
	@Test
	void testFindByIdLong() {
		Owner owner = ownerMapService.findById(ownerId);
		assertEquals(ownerId, owner.getId());
	}

	@Test
	void testSaveOwne() {
		Long id = 2L;
		Owner owner = Owner.builder().id(id).build();
		Owner savedOwner = ownerMapService.save(owner);
		assertEquals(id,  savedOwner.getId());
	}
	
	@Test
	void saveNoId() {
		Long id = 2L;
		Owner owner = Owner.builder().id(id).build();
		assertNotNull(owner);
		assertNotNull(owner.getId());
	}
	
	@Test
	void testDeleteOwner() {
		ownerMapService.delete(ownerMapService.findById(ownerId));
		assertEquals(0, ownerMapService.findAll().size());
	}

	@Test
	void testDeleById() {
		ownerMapService.deleById(ownerId);
		assertEquals(0, ownerMapService.findAll().size());
	}

	@Test
	void testFindByLastName() {
		String last = ownerMapService.findByLastName(lastName).getLastName();
		assertEquals(lastName, last);
	}
	
	@Test
	void testFindByLastNameNotFound() {
		Owner medina = ownerMapService.findByLastName("medina");
		assertNull(medina);
	}

}
