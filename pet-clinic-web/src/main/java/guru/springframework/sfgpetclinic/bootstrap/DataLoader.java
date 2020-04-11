package guru.springframework.sfgpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;


@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;
	

	
	public DataLoader(OwnerService ownerService, VetService vetService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
	}



	@Override
	public void run(String... args) throws Exception {

		Owner owner = new Owner();
		owner.setId(1L);
		owner.setFirstName("Michael");
		owner.setLastName("Weston");
		
		ownerService.save(owner);
		
		Owner owner2 = new Owner();
		owner2.setId(2L);
		owner2.setFirstName("Fiora");
		owner2.setLastName("Gleanne");
		
		ownerService.save(owner2);
		
		System.out.println("Loaded Owners");
		
		Vet vet = new Vet();
		vet.setId(1L);
		vet.setFirstName("Garen");
		vet.setLastName("Axe");
		
		vetService.save(vet);
		
		Vet vet2 = new Vet();
		vet2.setId(2L);
		vet2.setFirstName("GarenY");
		vet2.setLastName("Axe");
		
		vetService.save(vet2);
		
		System.out.println("Loaded vets");
	}

}
