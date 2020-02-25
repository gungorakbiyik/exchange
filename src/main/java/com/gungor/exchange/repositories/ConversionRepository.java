package com.gungor.exchange.repositories;

import com.gungor.exchange.domain.ConversionRec;
import org.springframework.data.repository.CrudRepository;

public interface ConversionRepository extends CrudRepository<ConversionRec, Long> {
}
