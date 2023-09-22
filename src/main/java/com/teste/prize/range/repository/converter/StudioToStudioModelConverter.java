package com.teste.prize.range.repository.converter;

import com.teste.prize.range.domain.Studio;
import com.teste.prize.range.repository.model.FilmModel;
import com.teste.prize.range.repository.model.StudioModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StudioToStudioModelConverter {
    private ModelMapper modelMapper;

    public StudioModel convert(final Studio studio) {
        return modelMapper.map(studio, StudioModel.class);
    }
}
