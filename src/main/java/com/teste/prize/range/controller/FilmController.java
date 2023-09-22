package com.teste.prize.range.controller;

import com.teste.prize.range.controller.converter.FilmProducerToFilmProducerResponseConverter;
import com.teste.prize.range.controller.converter.FilmRequestToFilmConverter;
import com.teste.prize.range.controller.converter.FilmStudioToFilmStudioResponseConverter;
import com.teste.prize.range.controller.converter.FilmToFilmResponseConverter;
import com.teste.prize.range.controller.request.FilmRequest;
import com.teste.prize.range.controller.response.FilmProducerResponse;
import com.teste.prize.range.controller.response.FilmResponse;
import com.teste.prize.range.controller.response.FilmStudioResponse;
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
@RequestMapping("api/v1/film")
public class FilmController {

    private final FilmSaveService filmSaveService;
    private final FilmUpdateService filmUpdateService;
    private final FilmGetAllService filmGetAllService;
    private final FilmDeleteService filmDeleteService;
    private final FilmSearchByIdService filmSearchByIdService;
    private final FilmProducerSaveService filmProducerSaveService;
    private final FilmProducerDeleteService filmProducerDeleteService;
    private final FilmStudioSaveService filmStudioSaveService;
    private final FilmStudioDeleteService filmStudioDeleteService;
    private final FilmToFilmResponseConverter filmToFilmResponseConverter;
    private final FilmRequestToFilmConverter filmRequestToFilmConverter;
    private final FilmProducerToFilmProducerResponseConverter filmProducerToFilmProducerResponseConverter;
    private final FilmStudioToFilmStudioResponseConverter filmStudioToFilmStudioResponseConverter;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<FilmResponse> getAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                     @RequestParam(required = false, defaultValue = "10") Integer size) {

        final var response = filmGetAllService.execute(page, size);

        if (response.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return response.map(filmToFilmResponseConverter::convert);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FilmResponse searchById(@PathVariable("id") Long id) {
        return filmSearchByIdService.execute(id)
                .map(filmToFilmResponseConverter::convert)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FilmResponse save(@RequestBody FilmRequest filmRequest) {
        return of(filmRequestToFilmConverter.convert(filmRequest))
                .map(filmSaveService::execute)
                .map(filmToFilmResponseConverter::convert)
                .orElseThrow();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FilmResponse update(@RequestBody FilmRequest filmRequest) {
        return of(filmRequestToFilmConverter.convert(filmRequest))
                .map(filmUpdateService::execute)
                .map(filmToFilmResponseConverter::convert)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        filmDeleteService.execute(id);
    }

    @PostMapping("/add/producer/{producerId}/film/{filmId}")
    @ResponseStatus(HttpStatus.CREATED)
    public FilmProducerResponse saveProducer(@PathVariable("producerId") Long producerId,
                                             @PathVariable("filmId") Long filmId) {
        return of(filmProducerSaveService.execute(producerId, filmId))
                .map(filmProducerToFilmProducerResponseConverter::convert)
                .orElseThrow();
    }

    @DeleteMapping("/delete/producer/{producerId}/film/{filmId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProducer(@PathVariable("producerId") Long producerId,
                               @PathVariable("filmId") Long filmId) {
        filmProducerDeleteService.execute(producerId, filmId);
    }

    @PostMapping("/add/studio/{studioId}/film/{filmId}")
    @ResponseStatus(HttpStatus.CREATED)
    public FilmStudioResponse saveStudio(@PathVariable("studioId") Long studioId,
                                         @PathVariable("filmId") Long filmId) {
        return of(filmStudioSaveService.execute(studioId, filmId))
                .map(filmStudioToFilmStudioResponseConverter::convert)
                .orElseThrow();
    }
    @DeleteMapping("/delete/studio/{studioId}/film/{filmId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudio(@PathVariable("studioId") Long studioId,
                             @PathVariable("filmId") Long filmId) {
        filmStudioDeleteService.execute(studioId, filmId);
    }
}
