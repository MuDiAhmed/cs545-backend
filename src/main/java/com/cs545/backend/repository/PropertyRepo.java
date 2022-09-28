package com.cs545.backend.repository;

import com.cs545.backend.entity.Property;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepo extends PagingAndSortingRepository<Property, Long> {
    @Query("select p from Property p join Request r on r.property = p where r.id = :requestId")
    Property findByRequestId(long requestId);
}
