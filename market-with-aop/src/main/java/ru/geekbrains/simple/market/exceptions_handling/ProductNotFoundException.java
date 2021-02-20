package ru.geekbrains.simple.market.exceptions_handling;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
