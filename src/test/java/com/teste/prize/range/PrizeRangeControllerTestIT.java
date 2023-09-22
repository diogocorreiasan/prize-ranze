package com.teste.prize.range;

import com.teste.prize.range.controller.PrizeRangeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PrizeRangeControllerTestIT {

    @Autowired
    private PrizeRangeController prizeRangeController;

    @Test
    void saveFilm(){
        var response = prizeRangeController.getPrizeRange();

        assertNotNull(response);
        assertEquals("Bo Derek", response.getMin().get(0).getProducer().getName());
        assertEquals(6, response.getMin().get(0).getInterval());
        assertEquals(1990, response.getMin().get(0).getFollowingWin());
        assertEquals(1984, response.getMin().get(0).getPreviousWin());

        assertEquals("Matthew Vaughn", response.getMax().get(0).getProducer().getName());
        assertEquals(13, response.getMax().get(0).getInterval());
        assertEquals(2015, response.getMax().get(0).getFollowingWin());
        assertEquals(2002, response.getMax().get(0).getPreviousWin());
    }
}
