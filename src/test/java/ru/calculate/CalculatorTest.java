package ru.calculate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
        Calculator calculator = new Calculator("2+2");

        int actualSolution = calculator.calculate();

        assertThat(actualSolution).isEqualTo(4);
    }

    /**
     * Тестирование метода calculate()
     * Вычитание
     */
    @ParameterizedTest()
    @ValueSource(strings = {"2-6", "997-1001"})
    void testCalculatorSubtraction(String expression) {
        Calculator calculator = new Calculator(expression);

        int actualSolution = calculator.calculate();

        assertThat(actualSolution).isEqualTo(-4);
    }

    /**
     * Тестирование метода calculate()
     * Вычитание
     */
    @ParameterizedTest()
    @ValueSource(strings = {"10-10-4", "10-4-10+2+2-4", "-10+2+4+-2+2"})
    void testCalculatorLongExpression (String expression) {
        Calculator calculator = new Calculator(expression);

        int actualSolution = calculator.calculate();

        assertThat(actualSolution).isEqualTo(-4);
    }

    /**
     * Тестирование метода calculate()
     * Оператор отсутствует
     */
    @Test
    void testCalculatorNoOperator() {
        Calculator calculator = new Calculator("1/1");

        Throwable thrown = catchThrowable(calculator::calculate);

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Operation sign not found in string");
    }

    /**
     * Тестирование метода calculate() неверный ввод чисел
     * Неверный аргумент
     */
    @ParameterizedTest()
    @ValueSource(strings = {"/+1", "1+$3"})
    void testCalculatorArgumentIsNotANumber (String expression) {
        Calculator calculator = new Calculator(expression);

        Throwable thrown = catchThrowable(calculator::calculate);

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is not a number!");
    }

    /**
     * Тестирование метода calculate() несколько операторов подряд (пропуск чисел)
     * Неверный аргумент
     */
    @ParameterizedTest()
    @ValueSource(strings = {"1--", "1++", "1+-", "++1", "--1"})
    void testCalculatorInvalidArgument(String expression) {
        Calculator calculator = new Calculator(expression);

        Throwable thrown = catchThrowable(calculator::calculate);

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is not a number!");
    }

    /**
     * Тестирование метода calculate()
     */
    @ParameterizedTest()
    @ValueSource(strings = {"1-", "1+", "1"})
    void testCalculatorInvalidException(String expression) {
        Calculator calculator = new Calculator(expression);

        Throwable thrown = catchThrowable(calculator::calculate);

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid expression");
    }

}