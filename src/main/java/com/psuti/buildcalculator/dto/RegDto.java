package com.psuti.buildcalculator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegDto extends AuthDto {
    private String firstname;
    private String lastname;
    private String surname;
    private String phone;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
}
