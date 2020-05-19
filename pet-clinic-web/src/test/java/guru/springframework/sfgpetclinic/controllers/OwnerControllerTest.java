package guru.springframework.sfgpetclinic.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.Matchers.hasSize;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;



/*
 *Created for jalemaov on 18-05-2020
 */
@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

	@Mock
	OwnerService ownerService;
	
	@InjectMocks
	OwnerController ownerController;
	
	Set<Owner> owners;
	
	MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		owners = new HashSet<>();
		owners.add(Owner.builder().id(1L).build());
		owners.add(Owner.builder().id(2L).build());
		
		/* MockMvcBuilders.standaloneSetup( . . . )
		 * Este método nos permite registrar uno o más Controllers sin necesidad de
		 * desplegar un WebApplicationContext completo. Simular las dependencias de los
		 * controladores empleando esta técnica es muy sencillo y nos permite probar un
		 * controlador aislado de sus dependencias
		 */		
		mockMvc = MockMvcBuilders
				.standaloneSetup(ownerController)
				.build();
	}

	@Test
	void testListOwners() throws Exception{
		when(ownerService.findAll()).thenReturn(owners);
		
		mockMvc.perform(get("/owners"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/index"))
				.andExpect(model().attribute("owners", hasSize(2)));
	}
	
	@Test
	void testListOwnersByIndex() throws Exception{
		owners.add(Owner.builder().id(3L).build());
		when(ownerService.findAll()).thenReturn(owners);
		
		mockMvc.perform(get("/owners/index"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/index"))
				.andExpect(model().attribute("owners", hasSize(3)));
	}

	@Test
	void testFindOwners() throws Exception{
		mockMvc.perform(get("/owners/find"))
				.andExpect(view().name("notImplement"))
				.andExpect(status().isOk());
		
		verifyNoInteractions(ownerService);
	}

}
