package com.egorka.lr2v1.service;


import com.egorka.lr2v1.model.Lamp;
import org.springframework.stereotype.Service;

@Service
public class LampFactoryImpl implements LampFactory {
    @Override
    public Lamp createLamp(int x, int y, String color) {
        return new Lamp(x, y, color);
    }


}
