package com.tpe.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please provide First Name")
    @Size(min = 4,max = 100,message = "First Name '${validatedValue}' must be between {min} and {max} chars long")
    @Column(length = 100, nullable = false)
    private String firstName;

    @NotBlank(message = "Please provide Last Name")
    @Size(min = 1,max = 100,message = "Last Name '${validatedValue}' must be between {min} and {max} chars long")
    @Column(length = 100, nullable = false)
    private String lastName;

    @NotNull(message = "Please provide Grade")
    private int grade;

    //555-555-5555
    //(555).555.5555
    //555 555 5555
    @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$", message = "Please provide valid phone number")
    @Column(length = 14)
    private String phoneNumber;


    @Column(length = 100,nullable = false,unique = true)
    @Email(message = "Provide valid email")
    private String email;

    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt = LocalDateTime.now();


}
