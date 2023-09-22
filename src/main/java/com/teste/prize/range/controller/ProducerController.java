package com.teste.prize.range.controller;

import com.teste.prize.range.controller.converter.ProducerRequestToProducerConverter;
import com.teste.prize.range.controller.converter.ProducerToProducerResponseConverter;
import com.teste.prize.range.controller.request.ProducerRequest;
import com.teste.prize.range.controller.response.ProducerResponse;
import com.teste.prize.range.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static java.util.Optional.of;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/producer")
public class ProducerController {
    private final ProducerSaveService producerSaveService;
    private final ProducerUpdateService producerUpdateService;
    private final ProducerGetAllService producerGetAllService;
    private final ProducerDeleteService producerDeleteService;
    private final ProducerSearchByIdService producerSearchByIdService;
    private final ProducerToProducerResponseConverter producerToProducerResponseConverter;
    private final ProducerRequestToProducerConverter producerRequestToProducerConverter;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ProducerResponse> getAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                         @RequestParam(required = false, defaultValue = "10") Integer size) {

        final var response = producerGetAllService.execute(page, size);

        if (response.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return response.map(producerToProducerResponseConverter::convert);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProducerResponse searchById(@PathVariable("id") Long id) {
        return producerSearchByIdService.execute(id)
                .map(producerToProducerResponseConverter::convert)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProducerResponse save(@RequestBody ProducerRequest producerRequest) {
        return of(producerRequestToProducerConverter.convert(producerRequest))
                .map(producerSaveService::execute)
                .map(producerToProducerResponseConverter::convert)
                .orElseThrow();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProducerResponse update(@RequestBody ProducerRequest producerRequest) {
        return of(producerRequestToProducerConverter.convert(producerRequest))
                .map(producerUpdateService::execute)
                .map(producerToProducerResponseConverter::convert)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        producerDeleteService.execute(id);
    }
}
