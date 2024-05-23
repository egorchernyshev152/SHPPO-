package com.egorka.lr2v1.Threads;

import com.egorka.lr2v1.service.Menu;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private final BlockingQueue<Task> taskQueue;
    private final BlockingQueue<Boolean> confirmationQueue;
    private final Scanner scanner;

    public Producer(BlockingQueue<Task> taskQueue, BlockingQueue<Boolean> confirmationQueue, Scanner scanner) {
        this.taskQueue = taskQueue;
        this.confirmationQueue = confirmationQueue;
        this.scanner = scanner;
    }

    @Override
    public synchronized void run() {
        while (true) {
            int choice;
                System.out.println("          Меню:");
                System.out.println("1. Нажать на кнопку");
                System.out.println("2. Привязать кнопку к лампе");
                System.out.println("3. Отвязать кнопку от лампы");
                System.out.println("4. Выйти");

                do {
                    // Запрос ввода от пользователя
                    choice = Menu.requestIntegerInput(scanner, "Ваше действие:");
                    if (choice < 1 || choice > 4) {
                        System.out.println("Неверный выбор. Повторите ввод.");
                    }
                } while (choice < 1 || choice > 4);

            try {
//        или     synchronized (taskQueue)
//                {
//                    taskQueue.put(new Task(choice));
//                    System.out.println("Производитель добавил запрос: '" + choice + "' в очередь задач");
//                }
                // Добавление задачи в очередь
                taskQueue.put(new Task(choice));
                System.out.println("Производитель добавил запрос: '" + choice + "' в очередь задач");
                if (choice == 4)
                {
                    break; // Завершение работы при выборе "Выйти"
                }
                confirmationQueue.take(); // Ожидаем подтверждения от потребителя
                // Обработка прерывания
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }
    }
}
