package com.sas.parking.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingCreateDTO {
    private String license;
    private String state;
    private String model;
    private String color;
}
