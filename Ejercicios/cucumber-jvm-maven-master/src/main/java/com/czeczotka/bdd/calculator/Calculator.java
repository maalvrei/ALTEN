package com.czeczotka.bdd.calculator;

public class Calculator {

    private int result;

    public void add(int arg1, int arg2) {
        result = arg1 + arg2;
    }
    public void subtract(int arg1, int arg2) {
        result = arg1 - arg2;
    }
    public void multiply(int arg1, int arg2) {
        result = arg1 * arg2;
    }
    public void divide(int arg1, int arg2) {
        result = arg1 / arg2;
    }
    public void elevateTo(int arg1, int arg2) {
        result = 1;
        for (int i = 0 ; i< arg2 ; i++) {
            result *= arg1;
        }
    }
    public int getResult() {
        return result;
    }
}
