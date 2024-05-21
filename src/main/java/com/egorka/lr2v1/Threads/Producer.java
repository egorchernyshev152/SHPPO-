package com.egorka.lr2v1.Threads;

import com.egorka.lr2v1.service.Menu;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private final BlockingQueue<Task> taskQueue;
    private final Scanner scanner;

    public Producer(BlockingQueue<Task> taskQueue, Scanner scanner) {
        this.taskQueue = taskQueue;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        while (true) {

            int choice;
            do
            {
                choice = Menu.requestIntegerInput(scanner, "Ваше действие:");
                if (choice < 1 || choice > 4) {
                    System.out.println("Неверный выбор. Повторите ввод.");
                }
            }
            while (choice < 1 || choice > 4);

            try
            {
                System.out.println("Продюсер добавил запрос" + choice + "в очередь задач");
                // Добавляем запрос в очередь задач
                taskQueue.put(new Task(choice));
                if (choice == 4) {
                    break;
                }
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }
    }
}
