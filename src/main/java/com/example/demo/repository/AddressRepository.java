package com.example.demo.repository;

import com.example.demo.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

//Created AddressBookRepository extending JpaRepository so that we perform CRUD and can implement custom query methods
public interface AddressRepository extends JpaRepository<Address, Integer> {

}