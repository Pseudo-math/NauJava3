package ru.mautaliev0aidar.NauJava.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mautaliev0aidar.NauJava.service.ItemService;

import java.time.LocalDate;

@Component
public class CommandProcessor {
    private final ItemService itemService;

    @Autowired
    public CommandProcessor(ItemService itemService) {
        this.itemService = itemService;
    }

    public void processCommand(String input) {
        String[] cmd = input.split(" ");

        try {
            switch (cmd[0]) {
                case "create" -> {
                    if (cmd.length < 5) {
                        System.out.println("Ошибка: недостаточно аргументов. Используйте: create <id> <name> <expirationDate> <lastLocation>");
                        return;
                    }
                    Long id = Long.parseLong(cmd[1]);
                    String name = cmd[2];
                    LocalDate expirationDate = LocalDate.parse(cmd[3]);
                    String lastLocation = cmd[4];
                    itemService.createItem(id, name, expirationDate, lastLocation);
                    System.out.println("Предмет успешно создан.");
                }
                case "find" -> {
                    if (cmd.length < 2) {
                        System.out.println("Ошибка: укажите ID. Используйте: find <id>");
                        return;
                    }
                    Long id = Long.parseLong(cmd[1]);
                    var item = itemService.findById(id);
                    System.out.println(item != null ? item : "Предмет не найден.");
                }
                case "delete" -> {
                    if (cmd.length < 2) {
                        System.out.println("Ошибка: укажите ID. Используйте: delete <id>");
                        return;
                    }
                    Long id = Long.parseLong(cmd[1]);
                    itemService.deleteById(id);
                    System.out.println("Предмет удалён.");
                }
                case "update-location" -> {
                    if (cmd.length < 3) {
                        System.out.println("Ошибка: недостаточно аргументов. Используйте: update-location <id> <newLocation>");
                        return;
                    }
                    Long id = Long.parseLong(cmd[1]);
                    String newLocation = cmd[2];
                    itemService.updateLastLocation(id, newLocation);
                    System.out.println("Местоположение обновлено.");
                }
                default -> System.out.println("Неизвестная команда.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка обработки команды: " + e.getMessage());
        }
    }
}
