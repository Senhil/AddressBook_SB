package com.example.demo.controller;

import com.example.demo.dto.AddressBookDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.exception.AddressBookException;
import com.example.demo.model.AddressBook;
import com.example.demo.service.IAddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/addressbook")

// create a class name as EmployeePayrollController
public class AddressBookController {

    @Autowired
    IAddressBookService service;

    @GetMapping("/welcome")
    public ResponseEntity<String> getWelcome() {
        String welcome = service.getWelcome();
        return new ResponseEntity<String>(welcome, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> addDataToRepo(@Valid @RequestBody AddressBookDTO addressBookDto) {
        AddressBook newAddressBook = service.postDataToRepo(addressBookDto);
        ResponseDTO responseDTO = new ResponseDTO("Record Added Succesfully", newAddressBook);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<String> getAllDataFromRepo() {
        List<AddressBook> listOfEmployee = service.getAllData();
        ResponseDTO responseDTO = new ResponseDTO("Record Retrieved Successfully", listOfEmployee);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<String> getDataFromRepoById(@PathVariable Integer id) throws AddressBookException {
        AddressBook existingEmployee = service.getDataById(id);
        ResponseDTO responseDTO = new ResponseDTO("Record for given ID Retrieved Successfully", existingEmployee);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateDataInRepo(@PathVariable Integer id,
                                                   @Valid @RequestBody AddressBookDTO addressBookDTO)
            throws AddressBookException {
        AddressBook updatedEmployee = service.updateDataById(id, addressBookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Record for particular ID Updated Successfully", updatedEmployee);
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDataInRepo(@PathVariable Integer id) throws AddressBookException {
        ResponseDTO responseDTO = new ResponseDTO
                ("Record for particular ID Deleted Successfully", service.deleteDataById(id));
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }
}