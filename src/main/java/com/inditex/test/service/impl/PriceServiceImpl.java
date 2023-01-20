package com.inditex.test.service.impl;

import com.inditex.test.dto.PriceRequestDto;
import com.inditex.test.dto.PriceResponseDto;
import com.inditex.test.model.Price;
import com.inditex.test.repository.PriceRepository;
import com.inditex.test.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public PriceResponseDto getPrice(PriceRequestDto priceRequestDto) {
        List<Price> list = priceRepository.getAllBetweenDates(priceRequestDto.getBrandId(), priceRequestDto.getProductId(), priceRequestDto.getDate());
        if(!list.isEmpty()){
            Price price = list.stream().findFirst().orElse(null);
            return PriceResponseDto.builder()
                    .brandId(price.getBrand().getId())
                    .productId(price.getProductId())
                    .startDate(price.getStartDate())
                    .endDate(price.getEndDate())
                    .priceList(price.getPriceList())
                    .price(price.getPrice())
                    .curr(price.getCurr().getCurrencyCode())
                    .build();
        } else
            return null;
    }
}
