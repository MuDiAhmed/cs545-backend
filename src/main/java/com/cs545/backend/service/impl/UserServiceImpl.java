package com.cs545.backend.service.impl;

import com.cs545.backend.dto.FavoriteDto;
import com.cs545.backend.dto.UserDto;
import com.cs545.backend.entity.Customer;
import com.cs545.backend.entity.Favorite;
import com.cs545.backend.entity.User;
import com.cs545.backend.mapper.FavoriteMapper;
import com.cs545.backend.mapper.UserMapper;
import com.cs545.backend.repository.UserRepo;
import com.cs545.backend.service.FavoriteService;
import com.cs545.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    private final FavoriteService favoriteService;

    @Override
    public Optional<UserDto> getByEmailOrUsername(String email, String username) {
        Optional<User> user = userRepo.getUserByEmailOrUsername(email, username);
        return user.map(userMapper::toDto);
    }

    @Override
    public Optional<User> getByAuthId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String id = jwt.getSubject();
        return userRepo.getUserByAuthId(id);
    }

    @Override
    public List<FavoriteDto> getUserFavoriteList() {
        Optional<User> user = getByAuthId();
        return user.map(foundUser -> {
            Customer customer = (Customer) foundUser;
            return favoriteService.getUserFavoriteList(customer);
        }).orElse(new ArrayList<>());
    }

    @Override
    public FavoriteDto addToFavoriteList(FavoriteDto favoriteDto) {
        Optional<User> user = getByAuthId();
        return user.map(foundUser -> {
            Customer customer = (Customer) foundUser;
            return favoriteService.addToFavoriteList(customer, favoriteDto);
        }).orElse(null);
    }
}
