package com.egorka.lr2v1.Threads;

import com.egorka.lr2v1.service.ControlPanelProxy;
import com.egorka.lr2v1.service.RequestHandlerChain;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private final BlockingQueue<Task> taskQueue;
    private final BlockingQueue<Boolean> confirmationQueue;
    private final ControlPanelProxy proxy;
    private final RequestHandlerChain handlerChain;

    public Consumer(BlockingQueue<Task> taskQueue, BlockingQueue<Boolean> confirmationQueue, ControlPanelProxy proxy, RequestHandlerChain handlerChain) {
        this.taskQueue = taskQueue;
        this.confirmationQueue = confirmationQueue;
        this.proxy = proxy;
        this.handlerChain = handlerChain;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Извлечение задачи из очереди
                Task task = taskQueue.take();
                int choice = task.getChoice();
                System.out.println("Потребитель взял запрос: '" + choice + "' из очереди задач");
                switch (choice) {
                    case 1:
                        // Обработка нажатия кнопки
                        synchronized (System.out) {
                            handlerChain.handleRequest(1, proxy, new Scanner(System.in));
                        }
                        break;
                    case 2:
                        // Привязка кнопки к лампе
                        synchronized (System.out) {
                            handlerChain.handleRequest(2, proxy, new Scanner(System.in));
                        }
                        break;
                    case 3:
                        // Отвязка кнопки от лампы
                        synchronized (System.out) {
                            handlerChain.handleRequest(3, proxy, new Scanner(System.in));
                        }
                        break;
                    case 4:
                        // Завершение работы
                        System.out.println("Завершение работы потребителя.");
                        return;
                    default:
                        throw new IllegalArgumentException("Неправильный выбор: " + choice);
                }
                confirmationQueue.put(true); // Отправляем подтверждение продюсеру
            }
        }
        // Обработка прерывания
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


