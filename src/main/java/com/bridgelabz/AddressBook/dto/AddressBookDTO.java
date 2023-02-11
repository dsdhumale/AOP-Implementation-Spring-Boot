package com.bridgelabz.AddressBook.dto;

import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressBookDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "Entered name invalid, Please enter valid name")
    private String name;
    @Pattern(regexp = "^[1-9]{2}\\s{0,1}[0-9]{10}$",message = "Entered phone number is invalid, Please enter valid phone number")
    private String phoneNumber;
    private String email;
    private String address;
    private String city;
    private String state;
    private long zipcode;
}
