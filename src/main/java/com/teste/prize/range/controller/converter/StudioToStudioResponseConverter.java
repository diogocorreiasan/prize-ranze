package com.teste.prize.range.controller.converter;

import com.teste.prize.range.controller.response.StudioResponse;
import com.teste.prize.range.domain.Studio;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StudioToStudioResponseConverter {
    private final ModelMapper modelMapper;

    public StudioResponse convert(final Studio studio) {
        return modelMapper.map(studio, StudioResponse.class);
    }
}
