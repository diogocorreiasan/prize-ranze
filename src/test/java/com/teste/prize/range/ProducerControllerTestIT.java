package com.teste.prize.range;

import com.teste.prize.range.controller.ProducerController;
import com.teste.prize.range.controller.request.ProducerRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProducerControllerTestIT {

    @Autowired
    private ProducerController producerController;

    @Test
    @Order(1)
    void saveFilm(){
        var producer = new ProducerRequest();
        producer.setName("Steven Spielberg");

        var response = producerController.save(producer);

        assertNotNull(response);
        assertEquals(producer.getName(), response.getName());
    }

    @Test
    @Order(2)
    void updateFilm(){
        var producer = new ProducerRequest();
        producer.setId(301L);
        producer.setName("Steven Spielberg");

        var response = producerController.update(producer);

        assertNotNull(response);
        assertEquals(producer.getName(), response.getName());
    }

    @Test
    @Order(3)
    void getById(){
        var response = producerController.searchById(301L);

        assertNotNull(response);
        assertEquals("Steven Spielberg", response.getName());
    }

    @Test
    @Order(4)
    void getAll(){
        var response = producerController.getAll(3, 7);

        assertNotNull(response);
        assertEquals(7, response.getSize());
    }

    @Test
    @Order(5)
    void delete(){
        producerController.delete(301L);
        Assertions.assertThrows(ResponseStatusException.class, () -> producerController.searchById(301L));
    }
}
