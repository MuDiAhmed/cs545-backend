package com.cs545.backend.controller;

import com.cs545.backend.dto.RequestDto;
import com.cs545.backend.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;
    @PreAuthorize("hasRole('CUSTOMER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id:[0-9]}/requests")
    public RequestDto create(@PathVariable Long id, @RequestBody RequestDto requestDto){
        return propertyService.submitRequest(id, requestDto);
    }
}
