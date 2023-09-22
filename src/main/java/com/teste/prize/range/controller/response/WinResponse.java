package com.teste.prize.range.controller.response;

import com.teste.prize.range.domain.Producer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WinResponse {
    private ProducerResponse producer;
    private int interval;
    private int previousWin;
    private int followingWin;
}
