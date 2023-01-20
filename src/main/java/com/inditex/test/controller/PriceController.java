package com.inditex.test.controller;

import com.inditex.test.dto.PriceRequestDto;
import com.inditex.test.dto.PriceResponseDto;
import com.inditex.test.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PriceController {

    @Autowired
    PriceService priceService;

    @PostMapping("/price")
    PriceResponseDto getPrices(@Valid @RequestBody PriceRequestDto priceRequestDto) {
        return priceService.getPrice(priceRequestDto);
    }
}
