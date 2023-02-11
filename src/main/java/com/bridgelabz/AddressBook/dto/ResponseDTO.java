package com.bridgelabz.AddressBook.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private String message;
    private Object data;
}
