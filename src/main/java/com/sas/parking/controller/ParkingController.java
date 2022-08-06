package com.sas.parking.controller;

import com.sas.parking.controller.dto.ParkingCreateDTO;
import com.sas.parking.controller.dto.ParkingDTO;
import com.sas.parking.controller.mapper.ParkingMapper;
import com.sas.parking.model.Parking;
import com.sas.parking.service.ParkingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {
    @Autowired
    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    @ApiOperation("Find All parkings")
    public ResponseEntity<List<ParkingDTO>> findAll() {
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> findById(String id) {
        Parking parking = parkingService.findById(id);
        ParkingDTO parkingDTO = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok().body(parkingDTO);
    }

    @PostMapping
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto) {
        Parking parking = parkingMapper.toParking(dto);
        Parking parkingSaved = parkingService.save(parking);
        ParkingDTO parkingDTO = parkingMapper.toParkingDTO(parkingSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingDTO);
    }
}
