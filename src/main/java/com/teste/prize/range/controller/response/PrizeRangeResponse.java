package com.teste.prize.range.controller.response;

import com.teste.prize.range.domain.Win;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrizeRangeResponse {
    private List<Win> min;
    private List<Win> max;
}
