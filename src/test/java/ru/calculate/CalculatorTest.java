package ru.calculate;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {

    @Test
    void calculate() {

        assertThat((new Calculator("2-6")).calculate()).isEqualTo(-4);
        assertThat((new Calculator("2+2")).calculate()).isEqualTo(4);
        assertThat((new Calculator("999+1001")).calculate()).isEqualTo(2000);
        assertThat((new Calculator("999-1001")).calculate()).isEqualTo(-2);
        assertThat((new Calculator("-999-1001")).calculate()).isEqualTo(-2000);
        assertThat((new Calculator("-10+-10")).calculate()).isEqualTo(-20);

        try {
            assertThat((new Calculator("1/1")).calculate()).isEqualTo(-2);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        try {
            assertThat((new Calculator("/+1")).calculate()).isEqualTo(-2);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }

}