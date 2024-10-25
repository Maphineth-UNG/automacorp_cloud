//package com.emse.spring.automacorp.dao;
//
//import com.emse.spring.automacorp.model.RoomEntity;
//import com.emse.spring.automacorp.model.SensorEntity;
//import com.emse.spring.automacorp.model.WindowEntity;
//import org.hibernate.sql.ast.tree.expression.JdbcParameter;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface RoomDao extends JpaRepository<RoomEntity, Long> {
//    @Query("select c from RoomEntity c where c.name=:name")
//    RoomEntity findByName(@Param("name") String name);
//
//    @Modifying // (3)
//    @Query("delete from RoomEntity c where c.name = ?1")
//    void deleteByName(String name);
//
////    List<RoomEntity> findByName(WindowEntity windows);
//
//}
//
//
//
//

package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomDao extends JpaRepository<RoomEntity, Long> {
    Optional<RoomEntity> findByName(String name);
}

