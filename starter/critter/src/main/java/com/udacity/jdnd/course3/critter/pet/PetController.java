package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.Entity.Customer;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.Service.CustomerService;
import com.udacity.jdnd.course3.critter.Service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO,pet);
        pet = petService.savePet(pet,petDTO.getOwnerId());

        PetDTO returnValue = new PetDTO();
        BeanUtils.copyProperties(pet,returnValue);
        returnValue.setOwnerId(petDTO.getOwnerId());

        return returnValue;
//        throw new UnsupportedOperationException();
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {

        Pet pet = petService.findById(petId);

        PetDTO returnValue = new PetDTO();
        BeanUtils.copyProperties(pet,returnValue);
        returnValue.setOwnerId(pet.getOwner().getUserId());

        return returnValue;
    }

    @GetMapping
    public List<PetDTO> getPets(){

        List<Pet> petList = petService.getPets();
        List<PetDTO> petDTOList = new ArrayList<>();
        for (Pet pet : petList) {
            PetDTO temp = new PetDTO();
            BeanUtils.copyProperties(pet,temp);
            temp.setOwnerId(pet.getOwner().getUserId());
            petDTOList.add(temp);
        }

        return petDTOList;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {

        List<Pet> petList = petService.getPetsByOwner(ownerId);
        List<PetDTO> petDTOList = new ArrayList<>();
        for (Pet pet : petList) {
            PetDTO temp = new PetDTO();
            BeanUtils.copyProperties(pet,temp);
            temp.setOwnerId(pet.getOwner().getUserId());
            petDTOList.add(temp);
        }

        return petDTOList;
//        throw new UnsupportedOperationException();
    }
}
