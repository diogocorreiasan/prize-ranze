package com.teste.prize.range.service.impl;

import com.teste.prize.range.adapter.FilmProducerAdapterRepository;
import com.teste.prize.range.adapter.ProducerAdapterRepository;
import com.teste.prize.range.domain.Film;
import com.teste.prize.range.domain.PrizeRange;
import com.teste.prize.range.domain.Producer;
import com.teste.prize.range.domain.Win;
import com.teste.prize.range.service.PrizeRangeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import static com.teste.prize.range.utils.Constants.*;

@Service
@AllArgsConstructor
public class PrizeRangeServiceImpl implements PrizeRangeService {

    private final ProducerAdapterRepository producerAdapterRepository;
    private final FilmProducerAdapterRepository filmProducerAdapterRepository;

    @Override
    public PrizeRange execute() {
        final var list = producerAdapterRepository.getAll()
                .stream()
                .map(this::getFilms)
                .flatMap(Collection::stream)
                .toList();

        final var max = list
                .stream()
                .filter(win -> win.getInterval() > ZERO)
                .max(Comparator.comparing(Win::getInterval))
                .stream()
                .toList();

        final var min = list
                .stream()
                .filter(win -> win.getInterval() > ZERO)
                .min(Comparator.comparing(Win::getInterval))
                .stream()
                .toList();

        return PrizeRange.builder().min(min).max(max).build();
    }

    private List<Win> getFilms(final Producer producer) {
        final var years = filmProducerAdapterRepository.findByProducer(producer.getId(), YES)
                .stream()
                .sorted(Comparator.comparing(Film::getYear))
                .map(Film::getYear)
                .toList();

        if(!years.isEmpty()) {
            final var winMax = getWin(years, producer, Boolean.TRUE);
            final var winMin = getWin(years, producer, Boolean.FALSE);
            return List.of(winMax, winMin);
        }

        return List.of();
    }

    private Win getWin(final List<Integer> years, final Producer producer, boolean toggle) {

        var biggestRange = ZERO;
        var firstYear = years.get(ZERO);
        int minInterval = Integer.MAX_VALUE;

        for (int i = ONE; i < years.size(); i++) {
            int currentYear = years.get(i);
            int lastYear = years.get(i - ONE);

            int interval = currentYear - lastYear;

            if (toggle){
                if (interval > biggestRange) {
                    biggestRange = interval;
                    firstYear = lastYear;
                }
            } else {
                if (interval < minInterval) {
                    minInterval = interval;
                    firstYear = lastYear;
                }
            }
        }

        return Win
                .builder()
                .producer(producer)
                .interval(biggestRange)
                .followingWin(firstYear + biggestRange)
                .previousWin(firstYear)
                .build();
    }
}
