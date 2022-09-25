package com.cs545.backend.mapper;

import com.cs545.backend.dto.PropertyDto;
import com.cs545.backend.entity.Property;
import org.mapstruct.Mapper;

@Mapper
public interface PropertyMapper extends BaseMapper<PropertyDto, Property> {
}
