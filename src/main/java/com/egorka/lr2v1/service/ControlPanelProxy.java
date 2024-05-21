package com.egorka.lr2v1.service;

public interface ControlPanelProxy {
    void visualize();
    void pressButton(int x, int y);
    void requestLampBinding();
    void requestLampUnlink();
    void shutdown();
}
