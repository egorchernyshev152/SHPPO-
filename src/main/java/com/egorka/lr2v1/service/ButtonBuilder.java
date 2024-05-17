package com.egorka.lr2v1.service;


import com.egorka.lr2v1.model.Button;
import org.springframework.stereotype.Service;
@Service
public class ButtonBuilder {
    private int x;
    private int y;

    public ButtonBuilder() {
        this.x = 0;
        this.y = 0;
    }

    public ButtonBuilder setX(int x) {
        this.x = x;
        return this;
    }

    public ButtonBuilder setY(int y) {
        this.y = y;
        return this;
    }

    public Button build() {
        return new Button(x, y);
    }

    public static ButtonBuilder builder() {
        return new ButtonBuilder();
    }
}
