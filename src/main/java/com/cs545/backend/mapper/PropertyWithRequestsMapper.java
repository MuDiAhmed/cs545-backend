package com.cs545.backend.mapper;

import com.cs545.backend.dto.PropertyDto;
import com.cs545.backend.dto.PropertyWithRequestsDto;
import com.cs545.backend.entity.Property;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface PropertyWithRequestsMapper extends BaseMapper<PropertyWithRequestsDto, Property> {
}
