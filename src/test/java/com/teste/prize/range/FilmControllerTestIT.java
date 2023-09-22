package com.teste.prize.range;

import com.teste.prize.range.controller.FilmController;
import com.teste.prize.range.controller.request.FilmRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FilmControllerTestIT {

    @Autowired
    private FilmController filmController;

    @Test
    @Order(1)
    void saveFilm(){
        var film = new FilmRequest();
        film.setTitle("Joker");
        film.setYear(2020);
        film.setWinner("yes");

        var response = filmController.save(film);

        assertNotNull(response);
        assertEquals("Joker", response.getTitle());
        assertEquals(2020, response.getYear());
    }

    @Test
    @Order(2)
    void updateFilm(){
        var film = new FilmRequest();
        film.setId(207L);
        film.setTitle("Joker");
        film.setYear(2019);
        film.setWinner("yes");

        var responseUpdate = filmController.update(film);

        assertNotNull(responseUpdate);
        assertEquals("Joker", responseUpdate.getTitle());
        assertEquals(2019, responseUpdate.getYear());
    }

    @Test
    @Order(3)
    void getById(){
        var responseSearchById = filmController.searchById(207L);

        assertNotNull(responseSearchById);
        assertEquals("Joker", responseSearchById.getTitle());
        assertEquals(2019, responseSearchById.getYear());
    }

    @Test
    @Order(4)
    void getAll(){
        var response = filmController.getAll(3, 7);

        assertNotNull(response);
        assertEquals(7, response.getSize());
    }

    @Test
    @Order(5)
    void delete(){
        filmController.delete(207L);
        Assertions.assertThrows( ResponseStatusException.class, () -> filmController.searchById(207L));
    }
}
