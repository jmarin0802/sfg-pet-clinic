package guru.springframework.sfgpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(SpecialtyService specialtyService, OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.specialtyService = specialtyService;
		this.petTypeService = petTypeService;
		this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

    	loadData();
    }

	private void loadData() {
		PetType dog = new PetType();
    	dog.setName("Dog");
    	PetType savedDogPetType = petTypeService.save(dog);
    	
    	PetType cat = new PetType();
    	dog.setName("Cat");
    	PetType savedCatPetType = petTypeService.save(cat );
    	
    	Speciality radiology = new Speciality();
    	radiology.setDescription("radiology");
    	Speciality savedRadiology = specialtyService.save(radiology); 
   
    	Speciality surgery = new Speciality();
    	surgery.setDescription("surgery");
    	Speciality savedSurgery = specialtyService.save(surgery); 

    	
    	Speciality dentistry = new Speciality();
    	dentistry.setDescription("dentistry");
    	Speciality savedDentitry = specialtyService.save(dentistry); 

    	
        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("Catedral 1330");
        owner1.setCity("Santiago");
        owner1.setTelephone("987654321");
        
        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirtDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("Catedral 1337");
        owner2.setCity("Santiago");
        owner2.setTelephone("987654322");
        Pet FionaPet = new Pet();
        FionaPet.setPetType(savedCatPetType);
        FionaPet.setOwner(owner2);
        FionaPet.setBirtDate(LocalDate.now());
        FionaPet.setName("Misu");
        owner2.getPets().add(FionaPet);
        
        ownerService.save(owner2);
        
        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Kled");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Brand");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);

        System.out.println("Loaded Vets....");
	}
}