package com.inditex.test.service;

import com.inditex.test.dto.PriceRequestDto;
import com.inditex.test.dto.PriceResponseDto;

public interface PriceService {

    PriceResponseDto getPrice(PriceRequestDto priceRequestDto);
}
