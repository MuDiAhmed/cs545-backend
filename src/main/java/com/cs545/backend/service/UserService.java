package com.cs545.backend.service;

import com.cs545.backend.dto.FavoriteDto;
import com.cs545.backend.dto.UserDto;
import com.cs545.backend.entity.Favorite;
import com.cs545.backend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserDto> getByEmailOrUsername(String email, String username);

    Optional<User> getByAuthId();

    List<Favorite> getUserFavoriteList();

    Favorite addToFavoriteList(FavoriteDto favoriteDto);
}
