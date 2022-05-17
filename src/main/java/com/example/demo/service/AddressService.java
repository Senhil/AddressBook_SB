package com.example.demo.service;

import com.example.demo.dto.AddressBookDTO;
import com.example.demo.exception.AddressBookException;
import com.example.demo.model.Address;
import com.example.demo.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Slf4j generates logger instance
@Service
@Slf4j
public class AddressService implements IAddressBookService {

    //Autowired repository class to inject its dependency
    @Autowired
    AddressRepository repository;

    //Created service method which serves controller api to return welcome message
    public String getWelcome() {
        return "Welcome to Address Book !";
    }

    //Created service method which serves controller api to post data
    public Address saveDataToRepo(AddressBookDTO addressBookDTO) {
        Address newAddress = new Address(addressBookDTO);
        repository.save(newAddress);
        return newAddress;
    }

    //Created service method which serves controller api to get record by id
    public Address getRecordById(Integer id) {
        Optional<Address> address = repository.findById(id);
        if (address.isPresent()) {
            return address.get();
        } else throw new AddressBookException("Addressbook id not found");
    }

    //Created service method which serves controller api to retrieve all records
    public List<Address> getRecord() {
        List<Address> addressBook = repository.findAll();
        log.info("Found all records in Address ");
        return addressBook;
    }

    //Created service method which serves controller api to update record by id
    public Address updateRecordById(Integer id, AddressBookDTO addressBookDTO) {
        Optional<Address> addressBook = repository.findById(id);
        if (addressBook.isEmpty()) {
            throw new AddressBookException("AddressBook details for id not found");
        }
        Address newBook = new Address(id, addressBookDTO);
        repository.save(newBook);
        return newBook;
    }

    //Created service method which serves controller api to delete record by id
    public String deleteRecordById(Integer id) {
        Optional<Address> newAddressBook = repository.findById(id);
        if (newAddressBook.isEmpty()) {
            throw new AddressBookException("Address Book Details not found");
        } else {
            repository.deleteById(id);
        }
        return null;
    }

    public List<Address> findProductsWithSorting(String Field){
        return repository.findAll(Sort.by(Sort.Direction.ASC,Field));

    }


    //Created service method which serves controller api to sort in ascending order
//    @Override
//    public List<Address> sortByPriceAscending() {
//        List<Address> getBookList = repository.getSortedReverseListOfBooks();
//        return getBookList;
//    }
//
//    //Created service method which serves controller api to sort in descending order
//    @Override
//    public List<Book> sortByPriceDescending() {
//        List<Book> getBookList = bookRepository.getSortedListOfBooks();
//        return getBookList;
//    }




//    public List<Address> getSortedBook(String searchQuery, String sortOrder) {
//        List<Address> AddressData;
//        if (sortOrder != null && !sortOrder.isEmpty()) {
//            if (sortOrder.equalsIgnoreCase("asc")) {
//                AddressData.sort((o1, o2) -> o1.getPrice().compareTo(o2.getPrice()));
//            } else if (sortOrder.equalsIgnoreCase("desc")) {
//                bookData.sort((o1, o2) -> o2.getPrice().compareTo(o1.getPrice()));
//            }
//        }
//        return bookData;
//    }
//    public Address sortByCity(AddressBookDTO addressBookDTO)
//    {
//
//
////        list<AddressBookDTO> List = new list<AddressBookDTO>();
////
////        for (:) {
////
//        }
}