package com.emse.spring.automacorp.model;


import com.emse.spring.automacorp.mapper.HeaterMapper;
import com.emse.spring.automacorp.mapper.WindowMapper;
import com.emse.spring.automacorp.record.Heater;
import com.emse.spring.automacorp.record.Window;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "SP_ROOM")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
    private Long Id;

    @Column(nullable = false)
    private Integer floor;

    @Column(nullable = false)
    private String name;

//    () is nullable = false
    @OneToOne()
    private SensorEntity currentTemperature;

    @Column
    private Double targetTemperature;

    @OneToMany(mappedBy = "room")
    private List<WindowEntity> windows;

    @OneToMany(mappedBy = "room")
    private List<HeaterEntity> heaters;

    @ManyToOne
    private BuildingEntity building;

    public RoomEntity() {
    }

    public RoomEntity(Long id, String name, Integer floor, SensorEntity currentTemperature, Double targetTemperature) {
        Long Id = id;
        this.name = name;
        this.floor = floor;
        this.currentTemperature = currentTemperature;
        this.targetTemperature = targetTemperature;
    }


    public RoomEntity(String name, SensorEntity currentTemperature, Integer floor) {
        this.name = name;
        this.currentTemperature = currentTemperature;
        this.floor = floor;
    }

    public RoomEntity(String name, SensorEntity currentTemperature, Double targetTemperature, Integer floor) {
        this.name = name;
        this.currentTemperature = currentTemperature;
        this.targetTemperature = targetTemperature;
        this.floor = floor;
    }


    public RoomEntity(Long id, Integer floor, String name, SensorEntity currentTemperature, Double targetTemperature, List<WindowEntity> windows, List<HeaterEntity> heaters, BuildingEntity building) {
        Id = id;
        this.name = name;
        this.floor = floor;
        this.currentTemperature = currentTemperature;
        this.targetTemperature = targetTemperature;
        this.windows = windows;
        this.building = building;
        this.heaters = heaters;
    }

//    public RoomEntity(Long id, Integer floor, String name, SensorEntity currentTemperature, Double targetTemperature, List<WindowEntity> windows, List<HeaterEntity> heaters) {
//        Id = id;
//        this.name = name;
//        this.floor = floor;
//        this.currentTemperature = currentTemperature;
//        this.targetTemperature = targetTemperature;
//        this.windows = windows;
//        this.heaters = heaters;
//    }
    public Long getId() {
        return this.Id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public SensorEntity getCurrentTemperature() {
        return currentTemperature;
    }
    public Double getCurrentTemperatureValue() {
        return currentTemperature.getValue();
    }
    public void setCurrentTemperature(SensorEntity currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public Double getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(Double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }


    public void setBuilding(BuildingEntity building) {
        this.building = building;
    }

    public List<WindowEntity> getWindows() {
        return windows;
    }

    public List<Window> getWindowsRecords() {
        return windows.stream().map(WindowMapper::of).collect(Collectors.toList());
    }
    public Long getBuildingId() {
        return building.getId();
    }

    public BuildingEntity getBuilding() {
        return building;
    }

    public void setWindows(List<WindowEntity> windows) {
        this.windows = windows;
    }
    public void setHeaters(List<HeaterEntity> heaters) {
        this.heaters = heaters;
    }
    public List<HeaterEntity> getHeaters() {
        return heaters;
    }
    public List<Heater> getHeatersRecords() {
        return heaters.stream().map(HeaterMapper::of).collect(Collectors.toList());
    }

}