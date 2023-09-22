package com.teste.prize.range.repository.converter;

import com.teste.prize.range.domain.Producer;
import com.teste.prize.range.domain.Studio;
import com.teste.prize.range.repository.model.ProducerModel;
import com.teste.prize.range.repository.model.StudioModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudioModelMergeStudioConverter {
    private final ModelMapper modelMapper;

    public StudioModel convert(final Long id, final Studio studio){
        final var model =  modelMapper.map(studio, StudioModel.class);
        return model.withId(id);
    }
}
