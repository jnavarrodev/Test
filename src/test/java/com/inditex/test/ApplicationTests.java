package com.inditex.test;

import com.inditex.test.dto.PriceRequestDto;
import com.inditex.test.dto.PriceResponseDto;
import com.inditex.test.repository.PriceRepository;
import com.inditex.test.service.PriceService;
import com.inditex.test.service.impl.PriceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApplicationTests {

    @Autowired
    PriceRepository repository;

    PriceService priceService;

    @BeforeEach
    void init() {
        priceService = new PriceServiceImpl(repository);
    }

    @Test
    void test_1_day_14_at_1000_should_return_3550_price() {
        // Given
        PriceRequestDto request = PriceRequestDto.builder()
                .brandId(1)
                .productId(35455L)
                .date(LocalDateTime.of(LocalDate.of(2020, 6, 14), LocalTime.of(10, 0, 0)))
                .build();

        // When
        PriceResponseDto result = priceService.getPrice(request);

        // Then
        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(35.50).setScale(2, RoundingMode.UNNECESSARY), result.getPrice());
    }

    @Test
    void test_2_day_14_at_1600_should_return_2545_price() {
        // Given
        PriceRequestDto request = PriceRequestDto.builder()
                .brandId(1)
                .productId(35455L)
                .date(LocalDateTime.of(LocalDate.of(2020, 6, 14), LocalTime.of(16, 0, 0)))
                .build();

        // When
        PriceResponseDto result = priceService.getPrice(request);

        // Then
        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(25.45).setScale(2, RoundingMode.UNNECESSARY), result.getPrice());
    }

    @Test
    void test_3_day_14_at_2100_should_return_3550_price() {
        // Given
        PriceRequestDto request = PriceRequestDto.builder()
                .brandId(1)
                .productId(35455L)
                .date(LocalDateTime.of(LocalDate.of(2020, 6, 14), LocalTime.of(21, 0, 0)))
                .build();

        // When
        PriceResponseDto result = priceService.getPrice(request);

        // Then
        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(35.50).setScale(2, RoundingMode.UNNECESSARY), result.getPrice());
    }


    @Test
    void test_4_day_15_at_1000_should_return_3050_price() {
        // Given
        PriceRequestDto request = PriceRequestDto.builder()
                .brandId(1)
                .productId(35455L)
                .date(LocalDateTime.of(LocalDate.of(2020, 6, 15), LocalTime.of(10, 0, 0)))
                .build();

        // When
        PriceResponseDto result = priceService.getPrice(request);

        // Then
        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(30.50).setScale(2, RoundingMode.UNNECESSARY), result.getPrice());
    }


    @Test
    void test_5_day_16_at_2100_should_return_3895_price() {
        // Given
        PriceRequestDto request = PriceRequestDto.builder()
                .brandId(1)
                .productId(35455L)
                .date(LocalDateTime.of(LocalDate.of(2020, 6, 16), LocalTime.of(21, 0, 0)))
                .build();

        // When
        PriceResponseDto result = priceService.getPrice(request);

        // Then
        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(38.95).setScale(2, RoundingMode.UNNECESSARY), result.getPrice());
    }


}
