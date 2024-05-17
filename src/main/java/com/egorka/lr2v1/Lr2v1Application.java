package com.egorka.lr2v1;

import com.egorka.lr2v1.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.util.Scanner;
import static com.egorka.lr2v1.service.Menu.requestIntegerInput;

@SpringBootApplication
public class Lr2v1Application {

    public static void main(String[] args)
    {
        ConfigurableApplicationContext context = SpringApplication.run(Lr2v1Application.class, args);
        ControlPanel controlPanel = context.getBean(ControlPanel.class);

        System.out.println("Добро пожаловать в программу управления!");
        System.out.println("                /\\_/\\");
        System.out.println("               ( o.o )");
        System.out.println("                > ^ <");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        ControlPanelProxyImpl proxy = new ControlPanelProxyImpl(controlPanel);

        System.out.println("Сгенерирована панель управления:");
        proxy.visualize();

        RequestHandlerChain handlerChain = new RequestHandlerChain();
        handlerChain.addHandler(new ButtonPressHandler());
        handlerChain.addHandler(new LampBindingHandler());
        handlerChain.addHandler(new LampUnlinkHandler());

        while (true) {
            System.out.println("          Меню:");
            System.out.println("1. Нажать на кнопку");
            System.out.println("2. Привязать кнопку к лампе");
            System.out.println("3. Отвязать кнопку от лампы");
            System.out.println("4. Выйти");

            int choice = requestIntegerInput(scanner, "Ваше действие:");
            handlerChain.handleRequest(choice, proxy, scanner);

            if (choice == 4) {
                System.out.println("До свидания!");
                proxy.shutdown();
                break;
            }
        }
    }
}
