package com.example.model;

public class Result {

    private int result;

    public Result(int result) {
        this.result = result;
    }

    public Result() {
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result1 = (Result) o;

        return result == result1.result;
    }

    @Override
    public int hashCode() {
        return result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "result=" + result +
                '}';
    }
}
