package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.WindowEntity;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

//import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class WindowDaoTest {
    @Autowired
    private WindowDao windowDao;

    @Test
    public void shouldfindAWindowById() {
        WindowEntity window = windowDao.getReferenceById(-10L); // (3)
        Assertions.assertThat(window.getName()).isEqualTo("Window 1");
        Assertions.assertThat(window.getWindowStatus().getValue()).isEqualTo(1.0);
    }



}