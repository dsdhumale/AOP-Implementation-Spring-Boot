package com.bridgelabz.AddressBook.service;

import java.util.List;

import com.bridgelabz.AddressBook.dto.AddressBookDTO;
import com.bridgelabz.AddressBook.model.AddressBookModel;

import jakarta.validation.Valid;

public interface IAddressBookService {

    public AddressBookModel create(AddressBookDTO addressBookDTO);

    public AddressBookModel getById(long id);

    public List<AddressBookModel> getAll();

    public AddressBookModel update(long id, AddressBookDTO addressBookDTO);

    public AddressBookModel deleteById(long id);

    public List<AddressBookModel> getByname(String name);

    public List<AddressBookModel> getByCity(String city);

    public String createByToken(@Valid AddressBookDTO addressBookDTO);

    public AddressBookModel getByToken(String token);

    public AddressBookModel updatebytoken(String token, @Valid AddressBookDTO addressBookDTO);

    public AddressBookModel deleteByToken(String token);

}
