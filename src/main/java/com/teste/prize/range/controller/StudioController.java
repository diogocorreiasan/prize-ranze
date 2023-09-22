package com.teste.prize.range.controller;

import com.teste.prize.range.controller.converter.StudioRequestToStudioConverter;
import com.teste.prize.range.controller.converter.StudioToStudioResponseConverter;
import com.teste.prize.range.controller.request.StudioRequest;
import com.teste.prize.range.controller.response.StudioResponse;
import com.teste.prize.range.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static java.util.Optional.of;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/studio")
public class StudioController {
    private final StudioSaveService studioSaveService;
    private final StudioUpdateService studioUpdateService;
    private final StudioGetAllService studioGetAllService;
    private final StudioDeleteService studioDeleteService;
    private final StudioSearchByIdService studioSearchByIdService;
    private final StudioToStudioResponseConverter studioToStudioResponseConverter;
    private final StudioRequestToStudioConverter studioRequestToStudioConverter;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<StudioResponse> getAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                       @RequestParam(required = false, defaultValue = "10") Integer size) {

        final var response = studioGetAllService.execute(page, size);

        if (response.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return response.map(studioToStudioResponseConverter::convert);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudioResponse searchById(@PathVariable("id") Long id) {
        return studioSearchByIdService.execute(id)
                .map(studioToStudioResponseConverter::convert)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudioResponse save(@RequestBody StudioRequest studioRequest) {
        return of(studioRequestToStudioConverter.convert(studioRequest))
                .map(studioSaveService::execute)
                .map(studioToStudioResponseConverter::convert)
                .orElseThrow();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudioResponse update(@RequestBody StudioRequest studioRequest) {
        return of(studioRequestToStudioConverter.convert(studioRequest))
                .map(studioUpdateService::execute)
                .map(studioToStudioResponseConverter::convert)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        studioDeleteService.execute(id);
    }
}
