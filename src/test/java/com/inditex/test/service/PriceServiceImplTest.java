package com.inditex.test.service;

import com.inditex.test.dto.PriceRequestDto;
import com.inditex.test.dto.PriceResponseDto;
import com.inditex.test.model.Brand;
import com.inditex.test.model.Price;
import com.inditex.test.repository.PriceRepository;
import com.inditex.test.service.impl.PriceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import static com.inditex.test.Utils.getTimeByDayAndMonth;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceServiceImplTest {

    @Mock
    PriceRepository repository;

    PriceService priceService;

    @BeforeEach
    void init() {
        priceService = new PriceServiceImpl(repository);
    }


    @Test
    void getPrice_with_no_results_should_return_null() {
        // Given
        when(repository.getAllBetweenDates(any(), any(), any())).thenReturn(new ArrayList<>());
        PriceRequestDto request = PriceRequestDto.builder()
                .brandId(1)
                .productId(1L)
                .date(getTimeByDayAndMonth(1, 1))
                .build();

        // When
        PriceResponseDto result = priceService.getPrice(request);

        // Then
        assertNull(result);
    }

    @Test
    void getPrice_same_date_should_return_higher_priority() {
        // Given
        when(repository.getAllBetweenDates(any(), any(), any())).thenReturn(generatePrices());
        PriceRequestDto request = PriceRequestDto.builder()
                .brandId(1)
                .productId(1L)
                .date(getTimeByDayAndMonth(1, 1))
                .build();

        // When
        PriceResponseDto result = priceService.getPrice(request);

        // Then
        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(100).setScale(1, RoundingMode.UNNECESSARY), result.getPrice());

    }

    private List<Price> generatePrices() {
        List<Price> prices = new ArrayList<>();
        prices.add(Price.builder()
                .id(1L)
                .productId(1L)
                .brand(Brand.builder().id(1L).name("ZARA").build())
                .startDate(getTimeByDayAndMonth(1, 6))
                .endDate(getTimeByDayAndMonth(15, 6))
                .price(BigDecimal.valueOf(100.0))
                .priceList(1L)
                .priority(1)
                .curr(Currency.getInstance("EUR"))
                .build());
        prices.add(Price.builder()
                .id(2L)
                .productId(1L)
                .brand(Brand.builder().id(1L).name("ZARA").build())
                .startDate(getTimeByDayAndMonth(1, 6))
                .endDate(getTimeByDayAndMonth(30, 6))
                .price(BigDecimal.valueOf(200.0))
                .priceList(1L)
                .priority(0)
                .curr(Currency.getInstance("EUR"))
                .build());
        prices.add(Price.builder()
                .id(3L)
                .productId(1L)
                .brand(Brand.builder().id(1L).name("ZARA").build())
                .startDate(getTimeByDayAndMonth(1, 7))
                .endDate(getTimeByDayAndMonth(30, 9))
                .price(BigDecimal.valueOf(150.0))
                .priceList(1L)
                .priority(0)
                .curr(Currency.getInstance("EUR"))
                .build());

        return prices;
    }

}
