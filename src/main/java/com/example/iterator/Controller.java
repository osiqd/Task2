package com.example.iterator;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.util.Duration;

import java.io.File;

public class Controller {
    @FXML
    private ImageView imageView;
    @FXML
    private TextField delayField;
    @FXML
    private TextField directoryField;
    @FXML
    private ComboBox<String> formatComboBox;

    private ConcreteAggregate conaggr;
    private Iterator iter;
    private Timeline time;

    @FXML
    public void initialize() {
        // Инициализация временной шкалы с анимацией
        time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        formatComboBox.getItems().addAll("PNG", "JPG"); // Добавляем форматы в ComboBoxф
    }

    @FXML
    public void chooseDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src/images"));
        File selectedDirectory = directoryChooser.showDialog(null);
        if (selectedDirectory != null) {
            directoryField.setText(selectedDirectory.getAbsolutePath());
            // Обновляем путь к изображениям в ConcreteAggregate
            String path = selectedDirectory.getAbsolutePath() + File.separator;
            String format = formatComboBox.getValue().toLowerCase(); // Получаем выбранный формат
            conaggr = new ConcreteAggregate(path, "." + format); // Обновляем путь и формат
            iter = conaggr.getIterator(); // Обновляем итератор
        }
    }

    @FXML
    public void startSlideshow() {
        try {
            int delay = Integer.parseInt(delayField.getText());
            if (delay <= 0) {
                System.out.println("Задержка должна быть больше нуля.");
                return;
            }

            time.getKeyFrames().clear(); // Очистка предыдущих кадров
            time.getKeyFrames().add(new KeyFrame(Duration.seconds(delay), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (iter != null && iter.hasNext(1)) {
                        imageView.setImage((Image) iter.next());
                    } else {
                        System.out.println("Нет доступных изображений для показа.");
                    }
                }
            }));
            time.setCycleCount(Timeline.INDEFINITE); // Установка бесконечного цикла
            time.play(); // Запуск временной шкалы
        } catch (NumberFormatException e) {
            System.out.println("Пожалуйста, введите корректное число для задержки.");
        }
    }

    @FXML
    public void stopSlideshow() {
        time.stop(); // Остановка анимации
    }

@FXML
public void previousImage() {
    if (iter != null) {
        // Возвращаемся к предыдущему изображению
        iter.previous();
        imageView.setImage((Image) iter.preview());
    }
}

@FXML
public void nextImage() {
    if (iter != null && iter.hasNext(1)) {
        imageView.setImage((Image) iter.next());
    }
}
}
