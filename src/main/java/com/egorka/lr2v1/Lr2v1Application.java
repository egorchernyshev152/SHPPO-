package com.egorka.lr2v1;

import com.egorka.lr2v1.Threads.Consumer;
import com.egorka.lr2v1.Threads.Producer;
import com.egorka.lr2v1.Threads.Task;
import com.egorka.lr2v1.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@SpringBootApplication
public class Lr2v1Application {

    public static void main(String[] args) {
        // Инициализация Spring Boot контекста
        ConfigurableApplicationContext context = SpringApplication.run(Lr2v1Application.class, args);
        ControlPanel controlPanel = context.getBean(ControlPanel.class);

        System.out.println("Добро пожаловать в программу управления!");
        System.out.println("                /\\_/\\");
        System.out.println("               ( o.o )");
        System.out.println("                > ^ <");
        System.out.println();

        // Инициализация компонентов
        Scanner scanner = new Scanner(System.in);
        ControlPanelProxyImpl proxy = context.getBean(ControlPanelProxyImpl.class);

        System.out.println("Сгенерирована панель управления:");
        proxy.visualize();

        RequestHandlerChain handlerChain = context.getBean(RequestHandlerChain.class);
        // Создание очереди задач
        BlockingQueue<Task> taskQueue = new ArrayBlockingQueue<>(10);
        BlockingQueue<Boolean> confirmationQueue = new ArrayBlockingQueue<>(10);

        // Создаем и запускаем потоки производителей и потребителей
        Thread producerThread = new Thread(new Producer(taskQueue, confirmationQueue, scanner));
        Thread consumerThread = new Thread(new Consumer(taskQueue, confirmationQueue, proxy, handlerChain));

        producerThread.start();
        consumerThread.start();

        try {
            // Ожидание завершения работы потоков
            producerThread.join();
            consumerThread.join();
            // Обработка прерывания
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Программа завершена.");
    }
}


