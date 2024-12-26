package com.example.builder;

import javafx.scene.layout.Pane;

public interface Builder {
    void lineBounds(float start, float stop);
    void linePaint(float measure);
    void lineMark(String measure);
    void addTitle(String name);
    Indicator build();
    Pane getMainPane(); // Метод для получения панели
}