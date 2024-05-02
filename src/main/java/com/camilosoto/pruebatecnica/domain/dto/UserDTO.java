package com.camilosoto.pruebatecnica.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean active;
}
