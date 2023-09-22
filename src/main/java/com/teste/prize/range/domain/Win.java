package com.teste.prize.range.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Win {
    private Producer producer;
    private int interval;
    private int previousWin;
    private int followingWin;
}
