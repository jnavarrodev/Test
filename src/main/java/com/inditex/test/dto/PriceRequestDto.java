package com.inditex.test.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceRequestDto {

    @NotNull
    private Long productId;
    @NotNull
    private LocalDateTime date;
    @NotNull
    private Integer brandId;

}
