package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entity.Customer;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.Repositoty.CustomerRepository;
import com.udacity.jdnd.course3.critter.Repositoty.PetRepository;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Pet findById(Long petId) {

        Optional<Pet> returnFromRepo = petRepository.findById(petId);

        if (returnFromRepo.isEmpty()) {
            throw new RuntimeException("NotFound Pet");
        }
        returnFromRepo.get().setOwner(customerRepository.findById(returnFromRepo.get().getOwner().getUserId()).get());
        return returnFromRepo.get();
    }

    public Pet savePet(Pet pet, Long ownerId) {
        Optional<Customer> ownerOptional = customerRepository.findById(ownerId);

        if (ownerOptional.isEmpty()) {
            throw new RuntimeException("NotFound Customer");
        }

        Customer owner = new Customer();
//
//        List<Pet> petList = new ArrayList<>();
//        if (owner.getPets() != null) {
//            petList = owner.getPets();
//        }
//        petList.add(pet);
//        owner.setPets(petList);
        owner.setUserId(ownerId);
        pet.setOwner(owner);
        return petRepository.save(pet);


//        owner = customerRepository.save(owner);

//        return petRepository.findById(owner.getPets().get(0).getPetId()).get();

    }

    public List<Pet> getPetsByOwner(Long ownerId) {
        return petRepository.findByOwnerUserId(ownerId);
//        return null ;
    }

    public List<Pet> getPets() {
        return petRepository.findAll();
    }


}
