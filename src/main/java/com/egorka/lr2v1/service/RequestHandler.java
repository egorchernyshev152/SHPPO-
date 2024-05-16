package com.egorka.lr2v1.service;

import java.util.Scanner;

public interface RequestHandler {
    void handleRequest(int choice, ControlPanelProxy proxy, Scanner scanner);
}