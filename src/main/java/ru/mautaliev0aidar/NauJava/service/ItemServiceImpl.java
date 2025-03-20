package ru.mautaliev0aidar.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mautaliev0aidar.NauJava.repository.ItemsRepository;
import ru.mautaliev0aidar.NauJava.entity.Item;
import jakarta.annotation.PostConstruct;
import ru.mautaliev0aidar.NauJava.config.Config;

import java.time.LocalDate;

@Service
public class ItemServiceImpl implements ItemService
{
    private final ItemsRepository itemRepository;
    private final Config.AppInfo appInfo;

    @Autowired
    public ItemServiceImpl(ItemsRepository itemRepository, Config.AppInfo appInfo) {
        this.itemRepository = itemRepository;
        this.appInfo = appInfo; // Теперь бин внедряется в конструктор
    }
    @Override
    public void createItem(Long id, String name, LocalDate expirationDate, String lastLocation)
    {
        Item newItem = new Item();
        newItem.setId(id);
        newItem.setName(name);
        newItem.setExpirationDate(expirationDate);
        newItem.setLastLocation(lastLocation);
        itemRepository.create(newItem);
    }
    @Override
    public Item findById(Long id)
    {
        return itemRepository.read(id);
    }
    @Override
    public void deleteById(Long id)
    {
        itemRepository.delete(id);
    }
    @Override
    public void updateLastLocation(Long id, String newLocation)
    {
        Item item = new Item();
        item.setId(id);
        item.setLastLocation(newLocation);
        itemRepository.update(item);
    }
    @PostConstruct
    public void init()
    {
        System.out.println("Приложение: " + appInfo.getName());
        System.out.println("Версия: " + appInfo.getVersion());
    }
}