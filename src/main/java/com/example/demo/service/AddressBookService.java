package com.example.demo.service;


import com.example.demo.dto.AddressBookDTO;
import com.example.demo.model.AddressBook;
import com.example.demo.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService {

    @Autowired
    AddressBookRepository repository;


    public List<AddressBook> getListOfAddresses() {
        List<AddressBook> addressBooks = repository.findAll();
        return addressBooks;
    }

    public AddressBook saveAddress(AddressBookDTO addressBookDTO) {
        AddressBook addressBook = new AddressBook(addressBookDTO);
        repository.save(addressBook);
        return addressBook;
    }

    public AddressBook getAddressbyId(Integer id) {
        Optional<AddressBook> addressBook = repository.findById(id);
        return addressBook.get();
    }

    public AddressBook updateDateById(Integer id, AddressBookDTO addressBookDTO) {
        AddressBook addressBook = new AddressBook(id, addressBookDTO);
        repository.save(addressBook);
        return addressBook;
    }

    public void deleteContact(Integer id) {
        repository.deleteById(id);
    }
}
