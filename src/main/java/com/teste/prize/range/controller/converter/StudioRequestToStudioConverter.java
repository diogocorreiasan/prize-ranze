package com.teste.prize.range.controller.converter;

import com.teste.prize.range.controller.request.StudioRequest;
import com.teste.prize.range.domain.Studio;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StudioRequestToStudioConverter {
    private final ModelMapper modelMapper;

    public Studio convert(final StudioRequest studioRequest) {
        return modelMapper.map(studioRequest, Studio.class);
    }
}
