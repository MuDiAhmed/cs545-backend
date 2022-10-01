package com.cs545.backend.dto.property;

import lombok.Data;

@Data
public class PropertyDto {
    private long id;
    private int views;
    private int numberOfRooms;
    private int price;
    private int size;
    private int numberOfBathrooms;
    private int year;
}
