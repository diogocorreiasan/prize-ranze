package com.teste.prize.range.service.impl;

import com.teste.prize.range.domain.Film;
import com.teste.prize.range.domain.Producer;
import com.teste.prize.range.domain.Studio;
import com.teste.prize.range.excetion.FileException;
import com.teste.prize.range.excetion.HeaderException;
import com.teste.prize.range.excetion.UriException;
import com.teste.prize.range.service.FilmBatchSaveService;
import com.teste.prize.range.service.ReadFileService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.*;

import static com.teste.prize.range.utils.Constants.*;
import static java.util.Arrays.stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReadFileServiceImpl implements ReadFileService {

    @Value("${file.name}")
    private String file;

    @Value("${file.header}")
    private String HEADER;

    private final FilmBatchSaveService filmBatchSaveService;

    @Override
    public void execute() {

        String pathFile = null;
        try {
            pathFile = ClassLoader.getSystemResource(file).toURI().getPath();
            final var file = new FileInputStream(pathFile);
            readFile(file);
        } catch (FileNotFoundException e) {
            throw new FileException(FILE_NOT_FOUND);
        } catch (URISyntaxException e) {
            throw new UriException(ILLEGAL_CHARACTER);
        } catch (HeaderException e) {
            throw new HeaderException(INVALID_HEADER);
        }
    }

    private void readFile(final InputStream fileInput) throws HeaderException {

        final var film = new ArrayList<Film>();
        try (var scanner = new Scanner(fileInput)) {
            scanner.useDelimiter(PATTERN);
            var header = scanner.next();
            validationHeader(header);
            while (scanner.hasNext()) {
                addFilm(scanner.next(), film);
            }
        }
        filmBatchSaveService.execute(film);
    }
    private void validationHeader(final String header) throws HeaderException {
        final var newHeader = header
                .replace(TARGET, EMPTY)
                .replace(REGEX, EMPTY);

        if (StringUtils.isEmpty(newHeader) || !HEADER.equals(newHeader)) {
            throw new HeaderException(INVALID_HEADER);
        }
    }

    private void addFilm(final String line, final List<Film> films) {
        final var field = line
                .replace(TARGET, EMPTY)
                .split(SEMICOLON);

        if (field.length < FIVE) {
            films.add(Film
                    .builder()
                    .year(Integer.parseInt(field[ZERO]))
                    .title(field[ONE])
                    .studios(getStudios(field[TWO]))
                    .producers(getProducers(field[TREE]))
                    .build());
        } else {
            films.add(Film
                    .builder()
                    .year(Integer.parseInt(field[ZERO]))
                    .title(field[ONE])
                    .studios(getStudios(field[TWO]))
                    .producers(getProducers(field[TREE]))
                    .winner(field[FOR])
                    .build());
        }
    }

    private List<Studio> getStudios(final String fields) {
        return stream(fields.split(REGEX))
                .map(field -> Studio.builder().name(field).build())
                .toList();
    }

    private List<Producer> getProducers(final String fields) {
        return stream(fields.split(REGEX))
                .map(field -> Producer.builder().name(field).build())
                .toList();
    }
}
