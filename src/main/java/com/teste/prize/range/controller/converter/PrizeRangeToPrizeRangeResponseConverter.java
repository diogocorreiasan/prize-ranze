package com.teste.prize.range.controller.converter;

import com.teste.prize.range.controller.response.PrizeRangeResponse;
import com.teste.prize.range.domain.PrizeRange;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PrizeRangeToPrizeRangeResponseConverter {
    private final ModelMapper modelMapper;

    public PrizeRangeResponse convert(final PrizeRange prizeRange){
        return modelMapper.map(prizeRange, PrizeRangeResponse.class);
    }
}
