//package com.emse.spring.automacorp.mapper;
//
//import com.emse.spring.automacorp.dto.RoomDto;
//
//import com.emse.spring.automacorp.model.RoomEntity;
//import com.emse.spring.automacorp.record.Room;
//
//
//public class RoomMapper {
//    public static Room of (RoomEntity room) {
//        return new Room(
//                room.getId(),
//                room.getFloor(),
//                room.getName(),
//                room.getCurrentTemperature() != null ? room.getCurrentTemperatureValue() : null,
//                room.getTargetTemperature()
//        );
//    }
//}

//package com.emse.spring.automacorp.mapper;
//
//import com.emse.spring.automacorp.model.RoomEntity;
//import com.emse.spring.automacorp.record.Room;
//
//public class RoomMapper {
//    public static Room of (RoomEntity room) {
//        return new Room(
//                room.getId(),
//                room.getFloor(),
//                room.getName(),
//                room.getCurrentTemperature() != null ? room.getCurrentTemperatureValue() : null,
//                room.getTargetTemperature()
////                room.getWindows() != null ? room.getWindowsRecords() : null,
////                room.getHeaters() != null ? room.getHeatersRecords() : null,
////                room.getBuilding() != null ? room.getBuildingId() : null
//        );
//    }
//}


package com.emse.spring.automacorp.mapper;

import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.record.Room;

public class RoomMapper {
    public static Room of (RoomEntity room) {
        return new Room(
                room.getId(),
                room.getName(),
                room.getFloor(),
                room.getCurrentTemperature() != null ? room.getCurrentTemperatureValue() : null,
                room.getTargetTemperature(),
                room.getWindows() != null ? room.getWindowsRecords() : null,
                room.getHeaters() != null ? room.getHeatersRecords() : null,
                room.getBuilding() != null ? room.getBuildingId() : null
        );
    }
}
