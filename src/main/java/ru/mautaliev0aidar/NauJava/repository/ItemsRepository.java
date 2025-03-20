package ru.mautaliev0aidar.NauJava.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.mautaliev0aidar.NauJava.entity.Item;

@Component
public class ItemsRepository implements CrudRepository<Item, Long>
{
    private final List<Item> itemContainer;
    @Autowired
    public ItemsRepository(List<Item> itemContainer)
    {
        this.itemContainer = itemContainer;
    }

    @Override
    public void create(Item item) {
        if (item.getId() > itemContainer.size())
        {
            for (int i = itemContainer.size(); i < item.getId(); i++)
            {
                itemContainer.add(null);
            }
        }
        itemContainer.add(item.getId().intValue(), item);
    }

    @Override
    public Item read(Long id) {
        if (id < itemContainer.size())
            return itemContainer.get(id.intValue());
        else
            return null;
    }

    @Override
    public void update(Item item) {
        if (item.getId() < itemContainer.size())
            itemContainer.set(item.getId().intValue(), item);
    }

    @Override
    public void delete(Long id) {
        if (id < itemContainer.size())
            itemContainer.set(id.intValue(), null);
    }
}
