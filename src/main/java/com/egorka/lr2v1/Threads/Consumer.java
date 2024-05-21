package com.egorka.lr2v1.Threads;

import com.egorka.lr2v1.service.ControlPanelProxy;
import com.egorka.lr2v1.service.RequestHandlerChain;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private final BlockingQueue<Task> taskQueue;
    private final ControlPanelProxy proxy;
    private final RequestHandlerChain handlerChain;

    public Consumer(BlockingQueue<Task> taskQueue, ControlPanelProxy proxy, RequestHandlerChain handlerChain) {
        this.taskQueue = taskQueue;
        this.proxy = proxy;
        this.handlerChain = handlerChain;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Task task = taskQueue.take();
                int choice = task.getChoice();
                switch (choice) {
                    case 1:
                        // Обработка нажатия кнопки
                        handlerChain.handleRequest(1,proxy,new Scanner(System.in));
                        break;
                    case 2:
                        // Привязка кнопки к лампе
                        handlerChain.handleRequest(2,proxy,new Scanner(System.in));
                        break;
                    case 3:
                        // Отвязка кнопки от лампы
                        handlerChain.handleRequest(3,proxy,new Scanner(System.in));
                        break;
                    case 4:
                        // Завершение работы
                        System.out.println("Завершение работы потребителя.");
                        return;
                    default:
                        throw new IllegalArgumentException("Неправильный выбор: " + choice);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}