package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.dao.RoomDao;
import com.emse.spring.automacorp.dao.SensorDao;
import com.emse.spring.automacorp.dao.HeaterDao;
import com.emse.spring.automacorp.dao.WindowDao;
import com.emse.spring.automacorp.dto.RoomCommand;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.SensorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomControllerTest {

    @Mock
    private RoomDao roomDao;

    @Mock
    private SensorDao sensorDao;

    @Mock
    private HeaterDao heaterDao;

    @Mock
    private WindowDao windowDao;

    @InjectMocks
    private RoomController roomController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        when(roomDao.findAll()).thenReturn(Collections.singletonList(new RoomEntity("Living Room", new SensorEntity(), 22.0, 1)));

        var rooms = roomController.findAll();

        assertNotNull(rooms);
        assertEquals(1, rooms.size());
        assertEquals("Living Room", rooms.get(0).name());
    }

    @Test
    void testFindById() {
        RoomEntity room = new RoomEntity("Living Room", new SensorEntity(), 22.0, 1);
        when(roomDao.findById(1L)).thenReturn(Optional.of(room));

        var foundRoom = roomController.findById(1L);

        assertNotNull(foundRoom);
        assertEquals("Living Room", foundRoom.name());
    }

    @Test
    void testCreateNewRoom() {
        RoomCommand command = new RoomCommand(1, "Living Room", 10.5, 20.0);
        SensorEntity sensor = new SensorEntity("Sensor1", 22.0, SensorType.TEMPERATURE);
        when(sensorDao.save(any())).thenReturn(sensor);
        when(roomDao.save(any())).thenReturn(new RoomEntity("Living Room", sensor, 20.0, 1));

        var response = roomController.create(command);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Living Room", response.getBody().name());
    }

    @Test
    void testDeleteRoom() {
        RoomEntity room = new RoomEntity("Living Room", new SensorEntity(), 22.0, 1);
        when(roomDao.findById(1L)).thenReturn(Optional.of(room));

        roomController.delete(1L);

        verify(heaterDao).deleteAll(room.getHeaters());
        verify(windowDao).deleteAll(room.getWindows());
        verify(roomDao).deleteById(1L);
    }

}

