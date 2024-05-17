package com.egorka.lr2v1.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;
@Service
public class LampBindingHandler implements RequestHandler {

    @Override
    public void handleRequest(int choice, ControlPanelProxy proxy, Scanner scanner) {
        if (choice == 2) {
            proxy.requestLampBinding();
            System.out.println("Новое состояние панели после привязки:");
            proxy.visualize();
        } else {
            System.out.println("Передача запроса следующему обработчику...");
        }
    }
}