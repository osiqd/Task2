package com.example.builder;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    private Pane indicatorPane; // Панель для отображения индикатора

    @FXML
    private TextField startField; // Поле для ввода начального значения
    @FXML
    private TextField stopField; // Поле для ввода конечного значения
    @FXML
    private TextField measureField; // Поле для ввода текущего значения
    @FXML
    private Button updateButton; // Кнопка для обновления индикатора
    @FXML
    private Text titleText; // Текст для заголовка

    private BuilderIndicatorMini builder; // Объект для построения индикатора
    private Director director; // Объект для директора

    @FXML
    public void initialize() {
        builder = new BuilderIndicatorMini();
        director = new Director(); // Создаем экземпляр директора
        builder.addTitle("Индикатор значений"); // Установка заголовка
        indicatorPane.getChildren().add(builder.getMainPane()); // Добавляем панель индикатора
    }

    @FXML
    private void updateIndicator() {
        try {
            float start = Float.parseFloat(startField.getText());
            float stop = Float.parseFloat(stopField.getText());
            float measure = Float.parseFloat(measureField.getText());

            // Используем директор для создания индикатора
            director.construct(builder, measure, start, stop);
            builder.show(measure); // Показываем индикатор
        } catch (NumberFormatException e) {
            // Обработка ошибки преобразования
            System.err.println("Пожалуйста, введите корректные числовые значения.");
        }
    }
}
