//package com.emse.spring.automacorp.controller;
//
//import com.emse.spring.automacorp.dao.SensorDao;
//import com.emse.spring.automacorp.dto.SensorCommand;
//import com.emse.spring.automacorp.dto.SensorDto;
//import com.emse.spring.automacorp.mapper.SensorMapper;
//import com.emse.spring.automacorp.model.SensorEntity;
//import jakarta.transaction.Transactional;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@CrossOrigin
//@RestController //
//@RequestMapping("/api/sensors") //
//@Transactional //
//public class SensorController {
//    private final SensorDao sensorDao;
//
//    public SensorController(SensorDao sensorDao) {
//        this.sensorDao = sensorDao;
//    }
//
//    @GetMapping //
//    public List<SensorDto> findAll() {
//        return sensorDao.findAll()
//                .stream()
//                .map(SensorMapper::of)
//                .sorted(Comparator.comparing(SensorDto::name))
//                .collect(Collectors.toList());
//    }
//
//    @GetMapping(path = "/{id}")
//    public SensorDto findById(@PathVariable Long id) {
//        return sensorDao.findById(id).map(SensorMapper::of).orElse(null); //
//    }
//
//    @PostMapping //
//    public ResponseEntity<SensorDto> create(@RequestBody SensorCommand sensor) { //
//        SensorEntity entity = new SensorEntity(sensor.sensorType(), sensor.name());
//        entity.setValue(sensor.value());
//        SensorEntity saved = sensorDao.save(entity);
//        return ResponseEntity.ok(SensorMapper.of(saved));
//    }
//
//    @PutMapping(path = "/{id}") //
//    public ResponseEntity<SensorDto> update(@PathVariable Long id, @RequestBody SensorCommand sensor) {
//        SensorEntity entity = sensorDao.findById(id).orElse(null);
//        if (entity == null) {
//            return ResponseEntity.badRequest().build();
//        }
//        entity.setValue(sensor.value());
//        entity.setName(String.valueOf(sensor.id()));
//        entity.setSensorType(sensor.sensorType());
//        // (11)
//        return ResponseEntity.ok(SensorMapper.of(entity));
//    }
//
//    @DeleteMapping(path = "/{id}")
//    public void delete(@PathVariable Long id) {
//        sensorDao.deleteById(id);
//    }
//}


package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.dao.SensorDao;
import com.emse.spring.automacorp.mapper.SensorMapper;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.record.Sensor;
import com.emse.spring.automacorp.dto.SensorCommand;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController // (1)
@RequestMapping("/api/sensors") // (2)
@Transactional // (3)
public class SensorController {
    private final SensorDao sensorDao;

    public SensorController(SensorDao sensorDao) {
        this.sensorDao = sensorDao;
    }

    @GetMapping // (5)
    public List<Sensor> findAll() {
        return sensorDao.findAll()
                .stream()
                .map(SensorMapper::of)
                .sorted(Comparator.comparing(Sensor::name))
                .collect(Collectors.toList());  // (6)
    }

    @GetMapping(path = "/{id}")
    public Sensor findById(@PathVariable Long id) {
        return sensorDao.findById(id).map(SensorMapper::of).orElse(null); // (7)
    }

    @PostMapping // (8)
    public ResponseEntity<Sensor> create(@RequestBody SensorCommand sensor) { // (9)
        SensorEntity entity = new SensorEntity(sensor.sensorType(), sensor.name());
        entity.setValue(sensor.value());
        SensorEntity saved = sensorDao.save(entity);
        return ResponseEntity.ok(SensorMapper.of(saved));
    }

    @PutMapping(path = "/{id}") // (10)
    public ResponseEntity<Sensor> update(@PathVariable Long id, @RequestBody SensorCommand sensor) {
        SensorEntity entity = sensorDao.findById(id).orElse(null);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        entity.setValue(sensor.value());
        entity.setName(sensor.name());
        entity.setSensorType(sensor.sensorType());
        // (11)
        return ResponseEntity.ok(SensorMapper.of(entity));
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        sensorDao.deleteById(id);
    }
}
