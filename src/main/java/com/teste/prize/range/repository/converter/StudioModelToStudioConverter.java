package com.teste.prize.range.repository.converter;

import com.teste.prize.range.domain.Studio;
import com.teste.prize.range.repository.model.StudioModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StudioModelToStudioConverter {
    private final ModelMapper modelMapper;

    public Studio convert(final StudioModel studioModel){
        return modelMapper.map(studioModel, Studio.class);
    }
}
