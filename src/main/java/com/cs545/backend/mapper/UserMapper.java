package com.cs545.backend.mapper;

import com.cs545.backend.dto.UserDto;
import com.cs545.backend.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserDto, User> {
}
