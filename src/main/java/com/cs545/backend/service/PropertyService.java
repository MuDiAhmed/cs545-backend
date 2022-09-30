package com.cs545.backend.service;

import com.cs545.backend.dto.RequestDto;

public interface PropertyService {
    RequestDto submitRequest(Long propertyId, RequestDto requestDto);
}

