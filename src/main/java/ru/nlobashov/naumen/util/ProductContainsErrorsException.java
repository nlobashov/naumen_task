package ru.nlobashov.naumen.util;

public class ProductContainsErrorsException extends Exception {

    public ProductContainsErrorsException(String errorMessage)
    {
        super(errorMessage);
    }

}
