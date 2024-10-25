//package com.emse.spring.automacorp.mapper;
//
//import com.emse.spring.automacorp.dto.Building;
//import com.emse.spring.automacorp.model.BuildingEntity;
//
//public class BuildingMapper {
//    public static <BuildingDto> building of (BuildingEntity building) {
//        return new building(
//                building.getId(),
//                building.getName(),
//                building.getOutsideTemperatureValue(),
//                building.getRoomsRecord()
//        );
//    }
//}

package com.emse.spring.automacorp.mapper;

import com.emse.spring.automacorp.model.BuildingEntity;
import com.emse.spring.automacorp.record.Building;

public class BuildingMapper {
    public static Building of(BuildingEntity building) {
        return new Building(
                building.getId(),
                building.getName(),
                building.getOutsideTemperatureValue(),
                building.getRoomsRecord()
        );
    }
}

