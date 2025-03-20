package ru.mautaliev0aidar.NauJava.storage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import ru.mautaliev0aidar.NauJava.entity.Item;

@Configuration
public class ConfigStorage
{
    @Bean
    @Scope (value = BeanDefinition.SCOPE_SINGLETON)
    public List<Item> itemContainer ()
    {
        return new ArrayList<>();
    }
}
