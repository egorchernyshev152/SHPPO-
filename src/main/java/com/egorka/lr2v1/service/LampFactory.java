package com.egorka.lr2v1.service;


import com.egorka.lr2v1.model.Lamp;

public interface LampFactory {
    Lamp createLamp(int x, int y, String color);
}

