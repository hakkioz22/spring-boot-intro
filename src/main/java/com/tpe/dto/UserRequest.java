package com.tpe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "Please provide firstName")
    private String firstName;

    @NotBlank(message = "Please provide lastName")
    private String lastName;

    @NotBlank(message = "Please provide userName")
    @Size(min = 5,max = 10,message = "Please provide a User Name min=5,max=10 chars long")
    private String userName;

    @NotBlank(message = "Please provide userName")
    private String password;
}
