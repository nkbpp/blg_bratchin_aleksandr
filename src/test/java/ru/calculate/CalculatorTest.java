package ru.calculate;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * Тестирование класса Calculator
 *
 * @author Александр Братчин
 * @version 1.0
 */

class CalculatorTest {

    /**
     * Тестирование метода calculate()
     * Сложение
     */
    @Test
    void testCalculatorAddition() {
        Calculator calculator1 = new Calculator("2+2");
        Calculator calculator2 = new Calculator("999+1001");

        int actualSolution1 = calculator1.calculate();
        int actualSolution2 = calculator2.calculate();

        assertThat(actualSolution1).isEqualTo(4);
        assertThat(actualSolution2).isEqualTo(2000);
    }

    /**
     * Тестирование метода calculate()
     * Вычитание
     */
    @Test
    void testCalculatorSubtraction() {
        Calculator calculator1 = new Calculator("2-6");
        Calculator calculator2 = new Calculator("999-1001");

        int actualSolution1 = calculator1.calculate();
        int actualSolution2 = calculator2.calculate();

        assertThat(actualSolution1).isEqualTo(-4);
        assertThat(actualSolution2).isEqualTo(-2);
    }

    /**
     * Тестирование метода calculate()
     * Отрицительные аргументы
     */
    @Test
    void testCalculatorNegativeNumber() {
        Calculator calculator1 = new Calculator("-999-1001");
        Calculator calculator2 = new Calculator("-10+-10");

        int actualSolution1 = calculator1.calculate();
        int actualSolution2 = calculator2.calculate();

        assertThat(actualSolution1).isEqualTo(-2000);
        assertThat(actualSolution2).isEqualTo(-20);
    }

    /**
     * Тестирование метода calculate()
     * Неверный оператор
     */
    @Test
    void testCalculatorInvalidOperator() {

        Throwable thrown = catchThrowable(() ->
                new Calculator("1/1")
        );

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Correct operations are '+' or '-'");
    }

    /**
     * Тестирование метода calculate()
     * Неверный аргумент
     */
    @Test
    void testCalculatorInvalidArgument() {
        Calculator calculator = new Calculator("/+1");

        Throwable thrown = catchThrowable(calculator::calculate);

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is not a number!");
    }


}