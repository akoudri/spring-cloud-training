package com.akfc.training.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Actor {

    private Long id;

    private String firstname;

    private String lastname;

    private String email;

    private LocalDate birthdate;

}