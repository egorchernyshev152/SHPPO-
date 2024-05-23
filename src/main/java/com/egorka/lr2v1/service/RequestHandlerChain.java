package com.egorka.lr2v1.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@Service
public class RequestHandlerChain {
    @Autowired
    private final List<RequestHandler> handlers = new ArrayList<>();

    public void handleRequest(int choice, ControlPanelProxy proxy, Scanner scanner) {
        for (RequestHandler handler : handlers) {
            handler.handleRequest(choice, proxy, scanner);
        }
    }

}
