package com.gungor.exchange.repositories;

import com.gungor.exchange.domain.ConversionRec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface ConversionRepository extends PagingAndSortingRepository<ConversionRec, Long> {


    @Query(value = "SELECT cr FROM ConversionRec cr " +
            "WHERE (:id is null or cr.id = :id) " +
            "and (:startDate is null or cr.transactionDate >= :startDate) " +
            "and (:endDate is null or cr.transactionDate <= :endDate)")
    Page<ConversionRec> findByIdAndStartDateAndEndDate(@Param("id") Long id, @Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);
}
