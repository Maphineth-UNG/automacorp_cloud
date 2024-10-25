package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.dao.RoomDao;
import com.emse.spring.automacorp.dao.SensorDao;
import com.emse.spring.automacorp.dao.WindowDao;
import com.emse.spring.automacorp.dto.WindowCommand;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.SensorType;
import com.emse.spring.automacorp.model.WindowEntity;
import com.emse.spring.automacorp.record.Window;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class WindowControllerTest {

    @InjectMocks
    private WindowController windowController;

    @Mock
    private WindowDao windowDao;

    @Mock
    private RoomDao roomDao;

    @Mock
    private SensorDao sensorDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Arrange
        WindowEntity window1 = new WindowEntity("Window1", new SensorEntity(), new RoomEntity());
        WindowEntity window2 = new WindowEntity("Window2", new SensorEntity(), new RoomEntity());
        when(windowDao.findAll()).thenReturn(Arrays.asList(window1, window2));

        // Act
        List<Window> windows = windowController.findAll();

        // Assert
        assertEquals(2, windows.size());
        verify(windowDao, times(1)).findAll();
    }

    @Test
    void testFindById_Success() {
        // Arrange
        Long windowId = 1L;
        WindowEntity windowEntity = new WindowEntity("Window1", new SensorEntity(), new RoomEntity());
        when(windowDao.findById(windowId)).thenReturn(Optional.of(windowEntity));

        // Act
        Window window = windowController.findById(windowId);

        // Assert
        assertNotNull(window);
        assertEquals("Window1", window.name());
        verify(windowDao, times(1)).findById(windowId);
    }

    @Test
    void testFindById_NotFound() {
        // Arrange
        Long windowId = 1L;
        when(windowDao.findById(windowId)).thenReturn(Optional.empty());

        // Act
        Window window = windowController.findById(windowId);

        // Assert
        assertNull(window);
        verify(windowDao, times(1)).findById(windowId);
    }

    @Test
    void testCreate() {
        // Arrange
        WindowCommand windowCommand = new WindowCommand("Window1", 1.0, 1L);
        SensorEntity sensor = new SensorEntity("SensorWindow1", 1.0, SensorType.STATUS);
        RoomEntity room = new RoomEntity();
        WindowEntity windowEntity = new WindowEntity("Window1", sensor, room);

        when(sensorDao.save(any(SensorEntity.class))).thenReturn(sensor);
        when(roomDao.findById(1L)).thenReturn(Optional.of(room));
        when(windowDao.save(any(WindowEntity.class))).thenReturn(windowEntity);

        // Act
        ResponseEntity<Window> response = windowController.create(windowCommand);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Window1", response.getBody().name());
        verify(sensorDao, times(1)).save(any(SensorEntity.class));
        verify(roomDao, times(1)).findById(1L);
        verify(windowDao, times(1)).save(any(WindowEntity.class));
    }

    @Test
    void testUpdate_Success() {
        // Arrange
        Long windowId = 1L;
        WindowCommand windowCommand = new WindowCommand("UpdatedWindow", 1.0, 1L);
        WindowEntity existingWindow = new WindowEntity("Window1", new SensorEntity(), new RoomEntity());

        when(windowDao.findById(windowId)).thenReturn(Optional.of(existingWindow));
        when(roomDao.findById(1L)).thenReturn(Optional.of(new RoomEntity()));

        // Act
        ResponseEntity<Window> response = windowController.update(windowId, windowCommand);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("UpdatedWindow", response.getBody().name());
        verify(windowDao, times(1)).findById(windowId);
        verify(roomDao, times(1)).findById(1L);
    }

    @Test
    void testUpdate_NotFound() {
        // Arrange
        Long windowId = 1L;
        WindowCommand windowCommand = new WindowCommand("Window1", 0.0,1L);
        when(windowDao.findById(windowId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Window> response = windowController.update(windowId, windowCommand);

        // Assert
        assertEquals(400, response.getStatusCodeValue());
        verify(windowDao, times(1)).findById(windowId);
    }

    @Test
    void testDelete() {
        // Arrange
        Long windowId = 1L;

        // Act
        windowController.delete(windowId);

        // Assert
        verify(windowDao, times(1)).deleteById(windowId);
    }
}
