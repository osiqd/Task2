package com.example.builder;

import javafx.scene.layout.Pane;

public class BuilderIndicatorMini implements Builder {
    private Indicator indicator;
    private Pane mainPane;
    private float start; // Храним начальную границу
    private float stop; // Храним конечную границу

    public BuilderIndicatorMini() {
        indicator = new Indicator();
        mainPane = new Pane(); // Создаем новую панель для индикатора
    }


    @Override
    public void addTitle(String title) {
    }

    @Override
    public void lineBounds(float start, float stop) {
        this.start = start; // Сохраняем начальную границу
        this.stop = stop; // Сохраняем конечную границу
        indicator.setPaint(0, start, stop); // Устанавливаем цвет по умолчанию
    }

    @Override
    public void linePaint(float measure) {
        indicator.setPaint(measure, start, stop); // Передаем значение и границы
        indicator.setMetka(measure, start, stop); // Устанавливаем метку
    }

    @Override
    public void lineMark(String measure) {
    }

    @Override
    public Pane getMainPane() {
        return mainPane; // Возвращаем панель индикатора
    }

    @Override
    public Indicator build() {
        return indicator; // Возвращаем созданный индикатор
    }

    public void show(float measure) {
        indicator.show(mainPane, start, stop, measure);
    }
}
