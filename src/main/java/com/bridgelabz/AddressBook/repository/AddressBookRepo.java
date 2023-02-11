package com.bridgelabz.AddressBook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.AddressBook.model.AddressBookModel;

public interface AddressBookRepo extends JpaRepository<AddressBookModel, Long> {

    List<AddressBookModel> findByName(String name);

    List<AddressBookModel> findByCity(String city);
}
