package com.example.builder;

public class Director {
    public Indicator construct(Builder builder, float value, float start, float stop) {
        builder.lineBounds(start, stop);
        builder.linePaint(value);
        builder.lineMark(String.format("%.1f", value));
        return builder.build();
    }
}