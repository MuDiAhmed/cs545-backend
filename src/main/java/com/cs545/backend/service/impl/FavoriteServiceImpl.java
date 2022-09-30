package com.cs545.backend.service.impl;

import com.cs545.backend.dto.FavoriteDto;
import com.cs545.backend.mapper.FavoriteMapper;
import com.cs545.backend.repository.FavoriteRepo;
import com.cs545.backend.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepo favoriteRepo;

    private final FavoriteMapper favoriteMapper;

    @Override
    public Optional<FavoriteDto> getByFavoriteListName(String name, Long userId) {
        return favoriteRepo
                .findByNameAndAndOwner_Id(name, userId)
                .map(favoriteMapper::toDto);
    }
}
