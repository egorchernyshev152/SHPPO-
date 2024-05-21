package com.egorka.lr2v1.service;

import java.util.Scanner;

public class Menu {
    public static int requestIntegerInput(Scanner scanner, String message) {
        System.out.println(message);
        while (!scanner.hasNextInt()) {
            System.out.println("Неверный ввод. Пожалуйста, введите целое число:");
            scanner.next(); // Пропускаем неверный ввод
        }
        return scanner.nextInt();
    }
}

