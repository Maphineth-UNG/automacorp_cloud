package com.emse.spring.automacorp.model;
import jakarta.persistence.*;

@Entity // .
@Table(name = "SP_SENSOR") // .
public class SensorEntity {
    @Id // .
    @GeneratedValue
    private Long id;

    @Column(nullable=false, length=255)  // .
    private String name;

    @Column(name = "sensor_value") //
    private Double value;

    @Column(name = "sensor_type") // .
    @Enumerated(EnumType.STRING) // .
    private SensorType sensorType;

    @ManyToOne
    private BuildingEntity building;

    @Transient // .
    private Integer notImportant;

    public SensorEntity() { // .
    }

    public SensorEntity(Long id, String name, Double value, SensorType sensorType) { // (8).
        this.id = id;
        this.name = name;
        this.value = value;
        this.sensorType = sensorType;
    }
    public SensorEntity(SensorType sensorType, String name) { // .
        this.name = name;
        this.sensorType = sensorType;
    }

    public SensorEntity(Long id, String name) { // .
        this.name = name;
        this.id = id;
    }

    public SensorEntity(String name, Double value, SensorType sensorType) { // (8).
        this.name = name;
        this.value = value;
        this.sensorType = sensorType;
    }


    public BuildingEntity getBuilding() {
        return building;
    }

    public void setBuilding(BuildingEntity building) {
        this.building = building;
    }

    public Long getBuildingId() {
        return building.getId();
    }
    public Long getId() { // .
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }

    public Integer getNotImportant() {
        return notImportant;
    }

    public void setNotImportant(Integer notImportant) {
        this.notImportant = notImportant;
    }
}

