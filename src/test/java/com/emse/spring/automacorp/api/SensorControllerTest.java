package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.dao.SensorDao;
import com.emse.spring.automacorp.mapper.SensorMapper;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.SensorType;
import com.emse.spring.automacorp.record.Sensor;
import com.emse.spring.automacorp.dto.SensorCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.MockedStatic;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.emse.spring.automacorp.model.SensorType.HUMIDITY;
import static com.emse.spring.automacorp.model.SensorType.TEMPERATURE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SensorControllerTest {

    @Mock
    private SensorDao sensorDao;

    @InjectMocks
    private SensorController sensorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testFindAll() {
        // Arrange
        SensorEntity sensor1 = new SensorEntity(1L, "Sensor 1", 23.5, TEMPERATURE);
        SensorEntity sensor2 = new SensorEntity(2L, "Sensor 2", 19.0, SensorType.HUMIDITY);
        List<SensorEntity> sensors = Arrays.asList(sensor1, sensor2);

        when(sensorDao.findAll()).thenReturn(sensors);

        // Mock static SensorMapper.of() method
        try (MockedStatic<SensorMapper> mockedMapper = mockStatic(SensorMapper.class)) {
            mockedMapper.when(() -> SensorMapper.of(sensor1))
                    .thenReturn(new Sensor(1L, "Sensor 1", 23.5, TEMPERATURE));
            mockedMapper.when(() -> SensorMapper.of(sensor2))
                    .thenReturn(new Sensor(2L, "Sensor 2", 19.0, SensorType.HUMIDITY));

            // Act
            List<Sensor> result = sensorController.findAll();

            // Assert
            assertEquals(2, result.size());
            assertEquals("Sensor 1", result.get(0).name());
            assertEquals("Sensor 2", result.get(1).name());

            verify(sensorDao, times(1)).findAll();
        }
    }

    @Test
    void testFindById_Success() {
        // Arrange
        Long id = 1L;
        SensorEntity sensorEntity = new SensorEntity(id, "Sensor 1", 23.5, TEMPERATURE);

        when(sensorDao.findById(id)).thenReturn(Optional.of(sensorEntity));

        // Mock static SensorMapper.of() method
        try (MockedStatic<SensorMapper> mockedMapper = mockStatic(SensorMapper.class)) {
            mockedMapper.when(() -> SensorMapper.of(sensorEntity))
                    .thenReturn(new Sensor(id, "Sensor 1", 23.5, TEMPERATURE));

            // Act
            Sensor result = sensorController.findById(id);

            // Assert
            assertNotNull(result);
            assertEquals("Sensor 1", result.name());
            verify(sensorDao, times(1)).findById(id);
        }
    }

    @Test
    void testCreate() {
        // Arrange
        SensorCommand sensorCommand = new SensorCommand(1L, "New Sensor", 25.5, TEMPERATURE);
        SensorEntity sensorEntity = new SensorEntity(SensorType.TEMPERATURE, "New Sensor");
        sensorEntity.setValue(25.5);

        when(sensorDao.save(any(SensorEntity.class))).thenReturn(sensorEntity);

        // Mock static SensorMapper.of() method
        try (MockedStatic<SensorMapper> mockedMapper = mockStatic(SensorMapper.class)) {
            mockedMapper.when(() -> SensorMapper.of(sensorEntity))
                    .thenReturn(new Sensor(1L, "New Sensor", 25.5, SensorType.TEMPERATURE));

            // Act
            ResponseEntity<Sensor> response = sensorController.create(sensorCommand);

            // Assert
            assertNotNull(response);
            assertEquals("New Sensor", response.getBody().name());
            assertEquals(25.5, response.getBody().value());
            verify(sensorDao, times(1)).save(any(SensorEntity.class));
        }
    }

    @Test
    void testUpdate_Success() {
        // Arrange
        Long id = 1L;
        SensorCommand sensorCommand = new SensorCommand(1L, "Updated Sensor", 50.0, HUMIDITY);
        SensorEntity sensorEntity = new SensorEntity(TEMPERATURE, "Old Sensor");
        sensorEntity.setValue(23.5);

        when(sensorDao.findById(id)).thenReturn(Optional.of(sensorEntity));

        // Mock static SensorMapper.of() method
        try (MockedStatic<SensorMapper> mockedMapper = mockStatic(SensorMapper.class)) {
            mockedMapper.when(() -> SensorMapper.of(sensorEntity))
                    .thenReturn(new Sensor(id, "Updated Sensor", 50.0, SensorType.HUMIDITY));

            // Act
            ResponseEntity<Sensor> response = sensorController.update(id, sensorCommand);

            // Assert
            assertNotNull(response);
            assertEquals("Updated Sensor", response.getBody().name());
            assertEquals(50.0, response.getBody().value());
            verify(sensorDao, times(1)).findById(id);
        }
    }

    @Test
    void testDelete() {
        // Arrange
        Long id = 1L;

        doNothing().when(sensorDao).deleteById(id);

        // Act
        sensorController.delete(id);

        // Assert
        verify(sensorDao, times(1)).deleteById(id);
    }
}

