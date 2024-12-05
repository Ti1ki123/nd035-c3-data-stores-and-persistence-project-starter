package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.Entity.Customer;
import com.udacity.jdnd.course3.critter.Entity.Employee;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.Service.CustomerService;
import com.udacity.jdnd.course3.critter.Service.EmployeeService;
import com.udacity.jdnd.course3.critter.Service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PetService petService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){

        Customer customerInput = new Customer();
        BeanUtils.copyProperties(customerDTO,customerInput);

        Customer resultinService= customerService.saveCustomer(customerInput);

        resultinService.setPets(petService.getPetsByOwner(resultinService.getUserId()));

        CustomerDTO returnValue = new CustomerDTO();
                BeanUtils.copyProperties(resultinService,returnValue);
        List<Long> petIds = new ArrayList<>();
        if (resultinService.getPets() != null) {
            for (int i = 0 ; i < resultinService.getPets().size(); i ++ ){
                petIds.add(resultinService.getPets().get(i).getPetId());
            }
        }
        returnValue.setPetIds(petIds);

        return returnValue;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){

        List<Customer> resultinService= customerService.getAllCustomers();

        List<CustomerDTO> returnValue = new ArrayList<CustomerDTO>();

//        resultinService.stream().map()

        for (int i = 0 ; i < resultinService.size(); i ++ ){
            CustomerDTO temp = new CustomerDTO();
            BeanUtils.copyProperties(resultinService.get(i),temp);
//            List<Pet> pets = resultinService.get(i).getPets();
            resultinService.get(i).getPets().forEach(pet -> temp.getPetIds().add(pet.getPetId()));
            returnValue.add(temp);
        }
//        BeanUtils.copyProperties(resultinService,returnValue);

        return returnValue;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){

        Customer resultinService= petService.findById(petId).getOwner();

        resultinService.setPets(petService.getPetsByOwner(resultinService.getUserId()));

        CustomerDTO returnValue = new CustomerDTO();
        BeanUtils.copyProperties(resultinService,returnValue);
        List<Long> petIds = new ArrayList<>();
        if (resultinService.getPets() != null) {
            for (int i = 0 ; i < resultinService.getPets().size(); i ++ ){
                petIds.add(resultinService.getPets().get(i).getPetId());
            }
        }
        returnValue.setPetIds(petIds);

        return returnValue;

//        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employeeInput = new Employee();
        BeanUtils.copyProperties(employeeDTO,employeeInput);

        Employee resultinService= employeeService.saveEmployee(employeeInput);

        EmployeeDTO returnValue = new EmployeeDTO();
        BeanUtils.copyProperties(resultinService,returnValue);

        return returnValue;
//        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {

        Employee resultinService= employeeService.getEmployee(employeeId);

        EmployeeDTO returnValue = new EmployeeDTO();
        BeanUtils.copyProperties(resultinService,returnValue);

        return returnValue;
//        throw new UnsupportedOperationException();
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {

         employeeService.setAvailability(daysAvailable,employeeId);

    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {

        List<Employee> returnFromService = employeeService.findEmployeesForService(employeeDTO.getSkills(), employeeDTO.getDate());
        List<EmployeeDTO> returnValue = new ArrayList<>();
        for (int i = 0 ; i < returnFromService.size(); i ++ ){
            EmployeeDTO temp = new EmployeeDTO();
            BeanUtils.copyProperties(returnFromService.get(i),temp);
            returnValue.add(temp);
        }
        return returnValue;
//        throw new UnsupportedOperationException();
    }

}
