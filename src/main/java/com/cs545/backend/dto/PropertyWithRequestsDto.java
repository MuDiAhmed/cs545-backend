package com.cs545.backend.dto;

import com.cs545.backend.entity.Request;
import lombok.Data;

import java.util.List;

@Data
public class PropertyWithRequestsDto {
    private long id;
    private List<Request> requests;
}
