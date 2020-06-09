package guru.springframework.sfgpetclinic.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;


import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.sfgpetclinic.controllers.OwnerController;
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
	void testFindOwners() throws Exception{
		mockMvc.perform(get("/owners/find"))
				.andExpect(view().name("/owners/findOwners"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("owner"));
		
		verifyZeroInteractions(ownerService);
	}

	@Test
	void processFindFormReturnMany() throws Exception{
		when(ownerService.findAllByLastNameLike(anyString()))
			.thenReturn(Arrays.asList(Owner.builder().id(1L).build(), 
				Owner.builder().id(2L).build()));
		
		mockMvc.perform(get("/owners"))
				.andExpect(status().isOk())
				.andExpect(view().name("/owners/ownerList"))
				.andExpect(model().attribute("selections", hasSize(2)));
	}
	
	@Test
	void processFindFormReturnOne() throws Exception{
		when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(Owner.builder().id(1L).build()));
		
		mockMvc.perform(get("/owners"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/1"));
	}
	
	@Test
	void displayOwner() throws Exception{
		when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());
		mockMvc.perform(get("/owners/1"))
				.andExpect(status().isOk())
				.andExpect(view().name("/owners/ownerDetails"))
				.andExpect(model().attribute("owner", hasProperty("id", is(1L))));
	}
}
