package com.bridgelabz.AddressBook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.AddressBook.dto.AddressBookDTO;
import com.bridgelabz.AddressBook.exception.AddressBookException;
import com.bridgelabz.AddressBook.model.AddressBookModel;
import com.bridgelabz.AddressBook.repository.AddressBookRepo;
import com.bridgelabz.AddressBook.util.EmailSenderUtil;
import com.bridgelabz.AddressBook.util.TokenUtil;

import jakarta.validation.Valid;

@Service
public class AddressBookService implements IAddressBookService {

    @Autowired
    AddressBookRepo addressBookRepo;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    EmailSenderUtil emailSender;

    @Override
    public AddressBookModel create(AddressBookDTO addressBookDTO) {
        AddressBookModel addressBookData = new AddressBookModel(addressBookDTO.getName(),
                addressBookDTO.getPhoneNumber(), addressBookDTO.getEmail(),
                addressBookDTO.getAddress(), addressBookDTO.getCity(),
                addressBookDTO.getState(), addressBookDTO.getZipcode());
        AddressBookModel addressBook = addressBookRepo.save(addressBookData);
        emailSender.sendEmail(addressBook.getEmail(), "Created Successfully",
                "WELCOME...." + addressBook.getName() + " your details are added!\n\n Name:  " + addressBook.getName()
                        + "\n Phone number:  " + addressBook.getPhoneNumber() + "\n Email:  " + addressBook.getEmail()
                        + "\n Address:  " + addressBook.getAddress() + "\n City:  " + addressBook.getCity()
                        + "\n State:  " + addressBook.getState());
        return addressBook;
    }

    @Override
    public AddressBookModel getById(long id) throws AddressBookException {
        AddressBookModel addressBookData = addressBookRepo.findById(id)
                .orElseThrow(() -> new AddressBookException("Address Book data not found for id " + id));
        emailSender.sendEmail(addressBookData.getEmail(), "Fetched Successfully",
                "WELCOME...." + addressBookData.getName() + " your details are fetched!\n\n Name:  "
                        + addressBookData.getName()
                        + "\n Phone number:  " + addressBookData.getPhoneNumber() + "\n Email:  "
                        + addressBookData.getEmail()
                        + "\n Address:  " + addressBookData.getAddress() + "\n City:  " + addressBookData.getCity()
                        + "\n State:  " + addressBookData.getState());
        return addressBookData;
    }

    @Override
    public List<AddressBookModel> getAll() {
        List<AddressBookModel> addressBookData = addressBookRepo.findAll();
        if (addressBookData.isEmpty()) {
            throw new AddressBookException("Address Book data is Empty ");
        } else {
            return addressBookData;
        }

    }

    @Override
    public AddressBookModel update(long id, AddressBookDTO addressBookDTO) throws AddressBookException {
        AddressBookModel addressBookData = addressBookRepo.findById(id)
                .orElseThrow(() -> new AddressBookException("Address Book data not found for id " + id));
        addressBookData.setName(addressBookDTO.getName());
        addressBookData.setPhoneNumber(addressBookDTO.getPhoneNumber());
        addressBookData.setEmail(addressBookDTO.getEmail());
        addressBookData.setAddress(addressBookDTO.getAddress());
        addressBookData.setCity(addressBookDTO.getCity());
        addressBookData.setState(addressBookDTO.getState());
        addressBookData.setZipcode(addressBookDTO.getZipcode());
        return addressBookRepo.save(addressBookData);
    }

    @Override
    public AddressBookModel deleteById(long id) {
        if (addressBookRepo.findById(id).isPresent()) {
            AddressBookModel addressBookModel = addressBookRepo.findById(id).get();
            addressBookRepo.deleteById(id);
            return addressBookModel;
        } else {
            throw new AddressBookException("Address book data not found for id " + id);
        }
    }

    @Override
    public List<AddressBookModel> getByname(String name) {
        List<AddressBookModel> addressBookModels = addressBookRepo.findByName(name);
        if (addressBookModels.isEmpty()) {
            throw new AddressBookException("Address book data not found for name " + name);
        } else {
            return addressBookModels;
        }
    }

    @Override
    public List<AddressBookModel> getByCity(String city) {
        List<AddressBookModel> addressBookModels = addressBookRepo.findByCity(city);
        if (addressBookModels.isEmpty()) {
            throw new AddressBookException("Address book data not found for city " + city);
        } else {
            return addressBookModels;
        }
    }

    @Override
    public String createByToken(@Valid AddressBookDTO addressBookDTO) {
        AddressBookModel addressBookData = new AddressBookModel(addressBookDTO.getName(),
                addressBookDTO.getPhoneNumber(), addressBookDTO.getEmail(),
                addressBookDTO.getAddress(), addressBookDTO.getCity(),
                addressBookDTO.getState(), addressBookDTO.getZipcode());
        AddressBookModel addressBookModel = addressBookRepo.save(addressBookData);
        long id = addressBookModel.getId();
        String token = tokenUtil.createToken(id);
        emailSender.sendEmail(addressBookModel.getEmail(), "Generated Token", "This is your token " + token);
        return token;
    }

    @Override
    public AddressBookModel getByToken(String token) throws AddressBookException {
        long id = tokenUtil.decodeToken(token);
        AddressBookModel addressBookData = addressBookRepo.findById(id)
                .orElseThrow(() -> new AddressBookException("Token is invalid "));
        return addressBookData;
    }

    @Override
    public AddressBookModel updatebytoken(String token, @Valid AddressBookDTO addressBookDTO) {
        long id = tokenUtil.decodeToken(token);
        AddressBookModel addressBookData = addressBookRepo.findById(id).get();
        addressBookData.setName(addressBookDTO.getName());
        addressBookData.setPhoneNumber(addressBookDTO.getPhoneNumber());
        addressBookData.setEmail(addressBookDTO.getEmail());
        addressBookData.setAddress(addressBookDTO.getAddress());
        addressBookData.setCity(addressBookDTO.getCity());
        addressBookData.setState(addressBookDTO.getState());
        addressBookData.setZipcode(addressBookDTO.getZipcode());
        return addressBookRepo.save(addressBookData);
    }

    @Override
    public AddressBookModel deleteByToken(String token) {
        long id = tokenUtil.decodeToken(token);
        if (addressBookRepo.findById(id).isPresent()) {
            AddressBookModel addressBookModel = addressBookRepo.findById(id).get();
            addressBookRepo.deleteById(id);
            return addressBookModel;
        } else {
            throw new AddressBookException("Invalid token ");
        }

    }
}
