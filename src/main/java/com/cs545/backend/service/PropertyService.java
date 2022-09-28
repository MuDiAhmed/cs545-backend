package com.cs545.backend.service;

import com.cs545.backend.dto.PropertyDto;
import com.cs545.backend.entity.Property;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface PropertyService {
    void save(PropertyDto propertyDto, long ownerId);
    List<Property> findAll(Pageable pageable);
    PropertyDto findById(long id);
    void deleteById(long id);
    void update(PropertyDto propertyDto, long l);
}

