package ru.mautaliev0aidar.NauJava.service;

import ru.mautaliev0aidar.NauJava.entity.Item;

import java.time.LocalDate;


public interface ItemService {
    void createItem(Long id, String name, LocalDate expirationDate, String lastLocation);
    Item findById(Long id);
    void deleteById(Long id);
    void updateLastLocation(Long id, String newLocation);
}
