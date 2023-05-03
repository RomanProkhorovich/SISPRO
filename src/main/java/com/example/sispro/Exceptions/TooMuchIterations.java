package com.example.sispro.Exceptions;

public class TooMuchIterations extends RuntimeException{
    public TooMuchIterations() {
        super("Введен некорректный цикл, проверьте условия");
    }
}
