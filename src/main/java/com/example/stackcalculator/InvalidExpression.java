package com.example.stackcalculator;

public class InvalidExpression extends RuntimeException {
    public InvalidExpression()
    {
        super("Invalid expression!!");
    }
    public InvalidExpression(String message)
    {
        super("Invalid expression!! "+message);
    }
}
