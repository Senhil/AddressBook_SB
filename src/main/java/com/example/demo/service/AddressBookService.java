package com.example.demo.service;

import com.example.demo.dto.AddressBookDTO;
import com.example.demo.exception.AddressBookException;
import com.example.demo.model.AddressBook;
import com.example.demo.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service

public class AddressBookService implements IAddressBookService {

    @Autowired
    AddressBookRepository repository;

    public String getWelcome() {
        return "Welcome to Address Book System !!!";
    }

    public AddressBook postDataToRepo(AddressBookDTO addressBookDTO) {
        AddressBook  newAddressBook  = new AddressBook (addressBookDTO);
        repository.save(newAddressBook);
        return newAddressBook ;
    }


    public List<AddressBook > getAllData() {
        List<AddressBook > list = repository.findAll();
        return list;
    }

    public AddressBook getDataById(Integer id) {
        Optional<AddressBook> newEmployee = repository.findById(id);
        if (newEmployee.isPresent()) {
            return newEmployee.get();
        } else throw new AddressBookException("Person id not found");
    }

    public AddressBook updateDataById(Integer id, AddressBookDTO addressBookDTO) {
        Optional<AddressBook> newEmployee = repository.findById(id);
        if (newEmployee.isPresent()) {
            AddressBook addressBook = new AddressBook(addressBookDTO);
            repository.save(addressBook);
            return addressBook;
        } else {
            throw new AddressBookException("Person Not found");
        }
    }

    public String deleteDataById(Integer id) {
        Optional<AddressBook> newEmployee = repository.findById(id);
        if (newEmployee.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new AddressBookException("Person Details not found");
        }
        return null;
    }
}
