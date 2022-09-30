package com.cs545.backend.service;

import com.cs545.backend.dto.FavoriteDto;

import java.util.Optional;

public interface FavoriteService {

    Optional<FavoriteDto> getByFavoriteListName(String name, Long userId);
}
