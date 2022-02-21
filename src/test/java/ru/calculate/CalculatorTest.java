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

        double actualSolution = calculator.calculate();

        assertThat(actualSolution).isEqualTo(4);
    }

    /**
     * Тестирование метода calculate()
     * Умножение
     */
    @Test
    void testCalculatorMultiply() {
        Calculator calculator = new Calculator("7*8");

        double actualSolution = calculator.calculate();

        assertThat(actualSolution).isEqualTo(56);
    }

    /**
     * Тестирование метода calculate()
     * Деление
     */
    @Test
    void testCalculatorDivide() {
        Calculator calculator = new Calculator("56/8");

        double actualSolution = calculator.calculate();

        assertThat(actualSolution).isEqualTo(7);
    }

    /**
     * Тестирование метода calculate()
     * Вычитание
     */
    @ParameterizedTest()
    @ValueSource(strings = {"2-6", "997-1001"})
    void testCalculatorSubtraction(String expression) {
        Calculator calculator = new Calculator(expression);

        double actualSolution = calculator.calculate();

        assertThat(actualSolution).isEqualTo(-4);
    }

    /**
     * Тестирование метода calculate()
     * длинные варажения
     */
    @ParameterizedTest()
    @ValueSource(strings = {"10-10-4", "10-4-10+2+2-4", "-10+2+4+-2+2", "10/2+2*3-15", "-4+4+10/2+2*3-15", "10+10/-2+2*3-15"})
    void testCalculatorLongExpression(String expression) {
        Calculator calculator = new Calculator(expression);

        double actualSolution = calculator.calculate();

        assertThat(actualSolution).isEqualTo(-4);
    }

    /**
     * Тестирование метода calculate()
     * Дробные числа
     */
    @ParameterizedTest()
    @ValueSource(strings = {"4*2.5", "2.5*4", "100-5.25*30-0.5+68"})
    void testCalculatorFractions(String expression) {
        Calculator calculator = new Calculator(expression);

        double actualSolution = calculator.calculate();

        assertThat(actualSolution).isEqualTo(10);
    }

    /**
     * Тестирование метода calculate() неверный ввод чисел
     * неизвестный символ
     */
    @ParameterizedTest()
    @ValueSource(strings = {"$+1", "1+$3", "1^1"})
    void testCalculatorUnknownCharacter(String expression) {
        Calculator calculator = new Calculator(expression);

        Throwable thrown = catchThrowable(calculator::calculate);

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Unexpected value");
    }

    /**
     * Тестирование метода calculate()
     * Проверка запятой
     */
    @ParameterizedTest()
    @ValueSource(strings = {".6+3", "6+3.", "6+.3", "6+-.3", "997-10..01"})
    void testCalculatorComma(String expression) {
        Calculator calculator = new Calculator(expression);

        Throwable thrown = catchThrowable(calculator::calculate);

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("'.'");
    }

    /**
     * Тестирование метода calculate()
     * несколько операторов подряд
     */
    @ParameterizedTest()
    @ValueSource(strings = {"++1", "--1", "1+--2"})
    void testCalculatorInvalidArgument(String expression) {
        Calculator calculator = new Calculator(expression);

        Throwable thrown = catchThrowable(calculator::calculate);

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Wrong order of operators");
    }

    /**
     * Тестирование метода calculate()
     * выражение оканчивается оператором
     */
    @ParameterizedTest()
    @ValueSource(strings = {"1--", "1++", "1+-"})
    void testCalculatorLastSymbol(String expression) {
        Calculator calculator = new Calculator(expression);

        Throwable thrown = catchThrowable(calculator::calculate);

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("The string does not end with a number");
    }

    /**
     * Тестирование метода calculate()
     * недостаточно символов для вычисления
     */
    @ParameterizedTest()
    @ValueSource(strings = {"1", "-1", "1+"})
    void testCalculatorNotEnoughCharacters(String expression) {
        Calculator calculator = new Calculator(expression);

        Throwable thrown = catchThrowable(calculator::calculate);

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Not enough characters");
    }

}