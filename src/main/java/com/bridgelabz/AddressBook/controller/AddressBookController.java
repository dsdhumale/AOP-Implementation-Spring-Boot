package com.bridgelabz.AddressBook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bridgelabz.AddressBook.dto.AddressBookDTO;
import com.bridgelabz.AddressBook.dto.ResponseDTO;
import com.bridgelabz.AddressBook.service.IAddressBookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/addressbookservice")
public class AddressBookController {

    @Autowired
    IAddressBookService addressBookService;

    // API for creating address book data
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@Valid @RequestBody AddressBookDTO addressBookDTO) {
        ResponseDTO response = new ResponseDTO("Address Book Data Created Successfully",
                addressBookService.create(addressBookDTO));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API for fetching particular address book data by id
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getDetailById(@PathVariable long id) {
        ResponseDTO response = new ResponseDTO("Address Book Data fetched Successfully ",
                addressBookService.getById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API for fetching all address book data
    @GetMapping("/getall")
    public ResponseEntity<ResponseDTO> getAllDetails() {
        ResponseDTO response = new ResponseDTO("All Address Book Data fetched Successfully ",
                addressBookService.getAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API for Update particular address book data data by id
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable long id,
            @Valid @RequestBody AddressBookDTO addressBookDTO) {
        ResponseDTO response = new ResponseDTO("Address Book Data Updated Successfully",
                addressBookService.update(id, addressBookDTO));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API for delete particular address book data by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteDetailById(@PathVariable long id) {
        ResponseDTO response = new ResponseDTO("Address Book Data Deleted Successfully ",
                addressBookService.deleteById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API for get particular address book by name
    @GetMapping("/getbyname/{name}")
    public ResponseEntity<ResponseDTO> getDetailByName(@PathVariable String name) {
        ResponseDTO response = new ResponseDTO("Address Book Data fetched Successfully ",
                addressBookService.getByname(name));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API for get particular address book by city
    @GetMapping("/getbycity/{city}")
    public ResponseEntity<ResponseDTO> getDetailByCity(@PathVariable String city) {
        ResponseDTO response = new ResponseDTO("Address Book Data fetched Successfully ",
                addressBookService.getByCity(city));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API for creating address book data by using jwt token
    @PostMapping("/createbytoken")
    public ResponseEntity<ResponseDTO> createByToken(@Valid @RequestBody AddressBookDTO addressBookDTO) {
        String token = addressBookService.createByToken(addressBookDTO);
        ResponseDTO response = new ResponseDTO("Address Book Data Created Successfully", token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API for fetching particular address book data by jwt token
    @CrossOrigin
    @GetMapping("/getbytoken/{token}")
    public ResponseEntity<ResponseDTO> getDetailByToken(@PathVariable String token) {
        ResponseDTO response = new ResponseDTO("Address Book Data fetched Successfully ",
                addressBookService.getByToken(token));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

     // API for Update particular address book data data by jwt token
     @PutMapping("/updatebytoken/{token}")
     public ResponseEntity<ResponseDTO> updatebyToken(@PathVariable String token,
             @Valid @RequestBody AddressBookDTO addressBookDTO) {
         ResponseDTO response = new ResponseDTO("Address Book Data Updated Successfully",
                 addressBookService.updatebytoken(token, addressBookDTO));
         return new ResponseEntity<>(response, HttpStatus.OK);
     }
 
     // API for delete particular address book data by id
     @DeleteMapping("/deletebytoken/{token}")
     public ResponseEntity<ResponseDTO> deleteByToken(@PathVariable String token) {
         ResponseDTO response = new ResponseDTO("Address Book Data Deleted Successfully ",
                 addressBookService.deleteByToken(token));
         return new ResponseEntity<>(response, HttpStatus.OK);
     }

}