package com.example.demo.controller;


import com.example.demo.dto.AddressBookDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.exception.AddressBookException;
import com.example.demo.model.Address;
import com.example.demo.service.IAddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

//Created controller so that we can perform rest api calls
@RestController
@RequestMapping("/addressbook")

public class AddressBookController {
    //Autowired IAddressBookService interface so we can inject its dependency here
    @Autowired
    IAddressBookService service;

    //Ability to get welcome message
    @GetMapping("/welcome")
    public ResponseEntity<String> getWelcome() {
        String message = service.getWelcome();
        return new ResponseEntity(message, HttpStatus.OK);
    }

    //Ability to store  address  record to repository
    @PostMapping("/create")
    public ResponseEntity<String> saveDataToRepo(@Valid @RequestBody AddressBookDTO addressBookDTO) {
        Address newAddress = service.saveDataToRepo(addressBookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Address Book Record created successfully", newAddress);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    //Ability to get all record from repository
    @GetMapping("/get")
    public ResponseEntity<String> getDataFromRepo() {
        List<Address> newAddress = service.getRecord();
        ResponseDTO responseDTO = new ResponseDTO("Address Book Record Sorted in Ascending order successfully", newAddress);
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }
    //Ability to get Record Sorted in Ascending order from repository layer
    @GetMapping("/{Field}")
    public ResponseEntity getDateBySorting(@PathVariable String Field) {
        List<Address> sortAddress = service.findProductsWithSorting(Field);
        ResponseDTO responseDTO = new ResponseDTO("Address Book Record for particular id retrieved successfully", sortAddress);
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }

    //Ability to get address  record by id
    @GetMapping("/get/{id}")
    public ResponseEntity<String> getRecordFromRepoByID(@PathVariable Integer id) throws AddressBookException {
        Optional<Address> newAddress = Optional.ofNullable(service.getRecordById(id));
        ResponseDTO responseDTO = new ResponseDTO("Address Book Record for particular id retrieved successfully", newAddress);
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }


    //Ability to update addressbook record to repository
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateRecordById(@PathVariable Integer id, @Valid @RequestBody AddressBookDTO addressBookDTO) throws AddressBookException {
        Address newAddress = service.updateRecordById(id, addressBookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Address Book Record updated successfully", newAddress);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    //Ability to delete address book record to repository
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRecordById(@PathVariable Integer id) throws AddressBookException {
        ResponseDTO dto = new ResponseDTO("Address Book Record updated successfully", service.deleteRecordById(id));
        return new ResponseEntity(dto, HttpStatus.OK);
    }
}