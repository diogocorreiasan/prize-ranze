package com.teste.prize.range;

import com.teste.prize.range.controller.StudioController;
import com.teste.prize.range.controller.request.StudioRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudioControllerTestIT {

    @Autowired
    private StudioController studioController;

    @Test
    @Order(1)
    void saveFilm(){
        var studio = new StudioRequest();
        studio.setName("Warner Bros7");

        var response = studioController.save(studio);

        assertNotNull(response);
        assertEquals(studio.getName(), response.getName());
    }

    @Test
    @Order(2)
    void updateFilm(){
        var studio = new StudioRequest();
        studio.setId(60L);
        studio.setName("Warner Bros");

        var response = studioController.update(studio);

        assertNotNull(response);
        assertEquals(studio.getName(), response.getName());
    }

    @Test
    @Order(3)
    void getById(){
        var response = studioController.searchById(60L);

        assertNotNull(response);
        assertEquals("Warner Bros", response.getName());
    }

    @Test
    @Order(4)
    void getAll(){
        var response = studioController.getAll(3, 7);

        assertNotNull(response);
        assertEquals(7, response.getSize());
    }

    @Test
    @Order(5)
    void delete(){
        studioController.delete(60L);
        Assertions.assertThrows( ResponseStatusException.class, () -> studioController.searchById(60L));
    }

}
