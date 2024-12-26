package com.example.iterator;

import javafx.scene.image.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConcreteAggregate implements Aggregate {
    private String directoryPath;
    private List<Image> images;

    public ConcreteAggregate(String directoryPath, String format) {
        this.directoryPath = directoryPath;
        loadImages(format); // Загружаем изображения при создании экземпляра с учетом формата
    }

    private void loadImages(String format) {
        images = new ArrayList<>();
        File folder = new File(directoryPath);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(format));

        if (files != null) {
            for (File file : files) {
                images.add(new Image(file.toURI().toString()));
            }
            System.out.println("Загружено изображений: " + images.size());
        } else {
            System.out.println("Не удалось загрузить изображения.");
        }
    }


    @Override
    public Iterator getIterator() {
        return new ImageIterator();
    }

    private class ImageIterator implements Iterator {
        private int current = 0;

        @Override
        public boolean hasNext(int x) {
            return !images.isEmpty(); // Проверка, что список не пустой
        }

        @Override
        public Object next() {
            if (hasNext(1)) {
                Image image = images.get(current);
                current = (current + 1) % images.size(); // Перемещаемся к следующему изображению, зацикливаем
                return image;
            }
            return null; // Если нет изображений, возвращаем null
        }

        @Override
        public Object preview() {
            if (!images.isEmpty()) {
                return images.get(current); // Возвращаем текущее изображение
            }
            return null; // Если нет изображений, возвращаем null
        }

        @Override
        public Object previous() {
            if (!images.isEmpty()) {
                current = (current - 1 + images.size()) % images.size(); // Возвращаемся к предыдущему изображению, зацикливаем
                return images.get(current);
            }
            return null; // Если нет изображений, возвращаем null
        }
    }
}
