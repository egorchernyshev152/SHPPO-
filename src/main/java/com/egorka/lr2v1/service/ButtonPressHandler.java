package com.egorka.lr2v1.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

import static com.egorka.lr2v1.service.Menu.requestIntegerInput;
@Service
public class ButtonPressHandler implements RequestHandler {

    @Override
    public void handleRequest(int choice, ControlPanelProxy proxy, Scanner scanner) {
        if (choice == 1) {
            int x = requestIntegerInput(scanner, "Введите координату кнопки (X):");
            int y = requestIntegerInput(scanner, "Введите координату кнопки (Y):");
            proxy.pressButton(x, y);
            System.out.println("Новое состояние панели:");
            proxy.visualize();
        } else {
            System.out.println("Передача запроса следующему обработчику...");
        }
    }
}
