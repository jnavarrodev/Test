package com.inditex.test.repository;

import com.inditex.test.model.Price;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends CrudRepository<Price, Integer> {

    @Query(value = "SELECT * FROM PRICES " +
            "INNER JOIN BRAND B ON B.BRAND_ID = :brandId " +
            "WHERE PRODUCT_ID = :productId " +
            "AND (:date BETWEEN START_DATE AND END_DATE)" +
            "ORDER BY PRIORITY DESC",
            nativeQuery = true)
    List<Price> getAllBetweenDates(
            @Param("brandId") Integer brandId,
            @Param("productId") Long productId,
            @Param("date") LocalDateTime date);

}
