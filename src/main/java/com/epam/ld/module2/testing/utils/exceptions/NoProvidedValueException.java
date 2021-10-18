package com.epam.ld.module2.testing.utils.exceptions;

public class NoProvidedValueException extends IndexOutOfBoundsException {
    public NoProvidedValueException(String errorMessage) {
        super(errorMessage);
    }
}