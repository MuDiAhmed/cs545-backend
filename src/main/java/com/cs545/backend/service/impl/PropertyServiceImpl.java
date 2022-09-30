package com.cs545.backend.service.impl;

import com.cs545.backend.dto.RequestDto;
import com.cs545.backend.entity.Property;
import com.cs545.backend.repository.PropertyRepo;
import com.cs545.backend.service.PropertyService;
import com.cs545.backend.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final RequestService requestService;
    private final PropertyRepo propertyRepo;

    @Override
    public RequestDto submitRequest(Long propertyId, RequestDto requestDto) {
        Optional<Property> property = propertyRepo.findById(propertyId);
        return property
                .map(foundProperty -> requestService.create(requestDto, foundProperty))
                .orElseThrow(() -> new RuntimeException("Property not found"));
    }
}
