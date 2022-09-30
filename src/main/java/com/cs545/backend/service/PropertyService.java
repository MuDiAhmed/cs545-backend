package com.cs545.backend.service;


import com.cs545.backend.dto.RequestDto;
import com.cs545.backend.dto.PropertyDto;
import com.cs545.backend.dto.PropertyWithRequestsDto;
import com.cs545.backend.entity.Property;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface PropertyService {
    RequestDto submitRequest(Long propertyId, RequestDto requestDto);
    void save(PropertyDto propertyDto);
    List<PropertyDto> findAll(Pageable pageable);
    PropertyDto findById(long id);
    PropertyDto findByRequestId(long requestId);
    List<PropertyWithRequestsDto> findWithRequests(Pageable pageable);
    void deleteById(long id);
    List<PropertyDto> findOwnerProperties(long ownerId, Pageable pageable);
}

