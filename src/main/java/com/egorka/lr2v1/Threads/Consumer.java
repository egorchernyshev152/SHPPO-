package com.egorka.lr2v1.Threads;

import com.egorka.lr2v1.service.ControlPanelProxy;
import com.egorka.lr2v1.service.RequestHandlerChain;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private final BlockingQueue<Task> taskQueue;
    private final Object confirmationLock;
    private final ControlPanelProxy proxy;
    private final RequestHandlerChain handlerChain;

    public Consumer(BlockingQueue<Task> taskQueue, Object confirmationLock, ControlPanelProxy proxy, RequestHandlerChain handlerChain) {
        this.taskQueue = taskQueue;
        this.confirmationLock = confirmationLock;
        this.proxy = proxy;
        this.handlerChain = handlerChain;
    }

    @Override
    public  void run()
    {
        try
        {
            while (true)
            {
                // Извлечение задачи из очереди
                Task task = taskQueue.take();
                int choice = task.getChoice();
                System.out.println("Потребитель взял запрос: '" + choice + "' из очереди задач");

                if (choice == 4)
                {
                    // Завершение работы
                    System.out.println("Завершение работы потребителя.");
                    return;
                }

                else
                {
                    handlerChain.handleRequest(choice, proxy, new Scanner(System.in));
                }

                synchronized (confirmationLock)
                {
                    confirmationLock.notify(); // Отправляем подтверждение продюсеру
                }
            }
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}
