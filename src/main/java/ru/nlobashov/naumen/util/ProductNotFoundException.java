package ru.nlobashov.naumen.util;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException()
    {
        super("Продукт не найден");
    }

}
