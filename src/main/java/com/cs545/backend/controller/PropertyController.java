package com.cs545.backend.controller;

import com.cs545.backend.dto.PropertyDto;
import com.cs545.backend.dto.PropertyWithRequestsDto;
import com.cs545.backend.service.PropertyService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {
    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public ResponseEntity<List<PropertyDto>> findAll(@RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseHelper.successResponse(propertyService.findAll(getPageable(pageNum, pageSize, sortBy)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDto> fetchPropertyDetails(@PathVariable long id) {
        return ResponseHelper.successResponse(propertyService.findById(id));
    }

    @GetMapping("/requests/{id}")
    public ResponseEntity<PropertyDto> fetchByRequestId(@PathVariable long id) {
        return ResponseHelper.successResponse(propertyService.findByRequestId(id));
    }

    @GetMapping("/requests")
    public ResponseEntity<List<PropertyWithRequestsDto>> fetchWithRequests(@RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseHelper.successResponse(propertyService.findWithRequests(getPageable(pageNum, pageSize, sortBy)));
    }

    @PostMapping
    public ResponseEntity<Void> createProperty(@RequestBody PropertyDto propertyDto) {
        propertyService.save(propertyDto);
        return ResponseHelper.successResponse();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable long id) {
        propertyService.deleteById(id);
        return ResponseHelper.successResponse();
    }

    private Pageable getPageable(int pageNum, int pageSize, String sortBy) {
        return PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
    }
}
