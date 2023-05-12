package com.example.sispro.Exceptions;

public class CantBeTwoOperandException extends RuntimeException{
    public CantBeTwoOperandException() {
        super("Должно быть 2 операнда");
    }
}
