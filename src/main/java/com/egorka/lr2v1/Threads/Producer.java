package com.egorka.lr2v1.Threads;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private final BlockingQueue<Task> taskQueue;
    private final Object confirmationLock;
    private final Scanner scanner;

    public Producer(BlockingQueue<Task> taskQueue, Object confirmationLock, Scanner scanner) {
        this.taskQueue = taskQueue;
        this.confirmationLock = confirmationLock;
        this.scanner = scanner;
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                // Получение выбора от пользователя
                int choice = getChoiceFromUser();

                // Создание новой задачи
                Task task = new Task(choice);

                // Добавление задачи в очередь
                    taskQueue.put(task);

                System.out.println("Производитель добавил задачу '" + choice + "' в очередь задач.");

                // Ожидание подтверждения от потребителя
                synchronized (confirmationLock)
                {
                    confirmationLock.wait();
                }

                if (choice == 4)
                {
                    // Завершение работы
                    System.out.println("Завершение работы производителя.");
                    return;
                }
            }
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    private int getChoiceFromUser() {
        System.out.println("          Меню:");
        System.out.println("1. Нажать на кнопку");
        System.out.println("2. Привязать кнопку к лампе");
        System.out.println("3. Отвязать кнопку от лампы");
        System.out.println("4. Выйти");


        return scanner.nextInt();
    }
}
