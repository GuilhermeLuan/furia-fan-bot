package dev.guilhermeluan.furiafanbot.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

public class KeyboardRowFactory {
    public static List<KeyboardRow> createRow(List<String> buttons) {
        KeyboardRow keyboardRow = new KeyboardRow();
        for (String button : buttons) {
            keyboardRow.add(button);
        }
        return List.of(keyboardRow);
    }
}
