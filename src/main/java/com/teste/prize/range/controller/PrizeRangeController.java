package com.teste.prize.range.controller;

import com.teste.prize.range.controller.converter.PrizeRangeToPrizeRangeResponseConverter;
import com.teste.prize.range.controller.response.PrizeRangeResponse;
import com.teste.prize.range.service.PrizeRangeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/prize-range")
public class PrizeRangeController {

    private final PrizeRangeService prizeRangeService;
    private final PrizeRangeToPrizeRangeResponseConverter prizeRangeToPrizeRangeResponseConverter;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PrizeRangeResponse getPrizeRange() {

        final var prizeRange = prizeRangeService.execute();
        return prizeRangeToPrizeRangeResponseConverter.convert(prizeRange);
    }
}
