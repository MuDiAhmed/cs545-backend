package com.cs545.backend.controller;

import com.cs545.backend.dto.FavoriteDto;
import com.cs545.backend.entity.Favorite;
import com.cs545.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/favorite-lists")
    @PreAuthorize("hasRole('CUSTOMER')")
    private List<Favorite> getFavoriteLists(){
        return userService.getUserFavoriteList();
    }

    @PutMapping("/favorite-lists")
    @PreAuthorize("hasRole('CUSTOMER')")
    private Favorite addToFavoriteLists(@RequestBody FavoriteDto favoriteDto){
        return userService.addToFavoriteList(favoriteDto);
    }

}
