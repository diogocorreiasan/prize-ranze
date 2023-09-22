package com.teste.prize.range.config;

import com.teste.prize.range.service.ReadFileService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ReadFileConfig {

    private final ReadFileService readFileService;

    @Bean
    public void ReadFileCSV() {
        readFileService.execute();
    }
}
