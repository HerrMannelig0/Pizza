package org.pizzaproject.model;

import javax.validation.constraints.Min;

public final class IngredientsNumber{

    @Min(value = 1, message = "Pizza must have at least one ingredient!")
    private int value;

    public IngredientsNumber() {
    }

    public IngredientsNumber(String number) {
        this.value = Integer.valueOf(number);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
