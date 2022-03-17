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

        String actualSolution = calculator.calculate();

        assertThat(actualSolution).isEqualTo("4");
    }

    /**
     * Тестирование метода calculate()
     * Умножение
     */
    @Test
    void testCalculatorMultiply() {
        Calculator calculator = new Calculator("7*8");

        String actualSolution = calculator.calculate();

        assertThat(actualSolution).isEqualTo("56");
    }

    /**
     * Тестирование метода calculate()
     * Деление
     */
    @Test
    void testCalculatorDivide() {
        Calculator calculator = new Calculator("56/8");

        String actualSolution = calculator.calculate();

        assertThat(actualSolution).isEqualTo("7");
    }

    /**
     * Тестирование метода calculate()
     * Вычитание
     */
    @ParameterizedTest()
    @ValueSource(strings = {"2-6", "997-1001"})
    void testCalculatorSubtraction(String expression) {
        Calculator calculator = new Calculator(expression);

        String actualSolution = calculator.calculate();

        assertThat(actualSolution).isEqualTo("-4");
    }

    /**
     * Тестирование метода calculate()
     * длинные варажения
     */
    @ParameterizedTest()
    @ValueSource(strings = {"10-10-4", "10-4-10+2+2-4", "-10+2+4+-2+2", "10/2+2*3-15", "-4+4+10/2+2*3-15", "10+10/-2+2*3-15"})
    void testCalculatorLongExpression(String expression) {
        Calculator calculator = new Calculator(expression);

        String actualSolution = calculator.calculate();

        assertThat(actualSolution).isEqualTo("-4");
    }

    /**
     * Тестирование метода calculate()
     * Дробные числа
     */
    @ParameterizedTest()
    @ValueSource(strings = {"4*2.5", "2.5*4", "100-5.25*30-0.5+68"})
    void testCalculatorFractions(String expression) {
        Calculator calculator = new Calculator(expression);

        String actualSolution = calculator.calculate();

        assertThat(actualSolution).isEqualTo("10");
    }

    /**
     * Тестирование метода calculate()
     * Скобки
     */
    @ParameterizedTest()
    @ValueSource(strings = {"(2+2)", "8/(1+1)", "0.5*(1+1)+3", "(2-6.4)*(8.4-10.4)*40.3-350.64"})
    void testCalculatorBrackets(String expression) {
        Calculator calculator = new Calculator(expression);

        String actualSolution = calculator.calculate();

        assertThat(actualSolution).isEqualTo("4");
    }

    /**
     * Тестирование метода calculate()
     * Скобки нарушен порядок
     */
    @ParameterizedTest()
    @ValueSource(strings = {"(2+2))", "((2+2)", "(())", "2+)2"})
    void testCalculatorBracketsErr(String expression) {
        Calculator calculator = new Calculator(expression);

        Throwable thrown = catchThrowable(calculator::calculate);

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Parentheses out of order");
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

    /**
     * Тестирование метода calculate()
     * большие числа
     */
    @ParameterizedTest()
    @ValueSource(strings = {"(20000000000000000000000000-600000000300000000.4)*(8.4-1000000000000000000000.4)*40.3"})
    void testBigNumbers(String expression) {
        Calculator calculator = new Calculator(expression);

        String actualSolution = calculator.calculate();

        assertThat(actualSolution).isEqualTo("-805999975819999987903551984073440000096720000128.96");
    }

    /**
     * Тестирование метода calculate()
     * Параметры
     */
    @ParameterizedTest()
    @ValueSource(strings = {"x0+x1"})
    void testCalculatorCalculateWithParams(String expression) {
        Calculator calculator = new Calculator(expression);

        String actualSolution = calculator.calculate("2", "5");

        assertThat(actualSolution).isEqualTo("7");
    }

    /**
     * Тестирование метода calculate()
     * Папаметры
     */
    @ParameterizedTest()
    @ValueSource(strings = {"x0+x1"})
    void testCalculatorCalculateWithParamNoParam(String expression) {
        Calculator calculator = new Calculator(expression);

        Throwable thrown = catchThrowable(() -> calculator.calculate("2"));

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("No param");
    }

    /**
     * Тестирование метода calculate()
     * Параметры в функциях
     */
    @ParameterizedTest()
    @ValueSource(strings = {"pow(x0+x1,x2+x2)+x3"})
    void testCalculatorFunctionsAndParams(String expression) {
        Calculator calculator = new Calculator(expression);

        String actualSolution = calculator.calculate("2", "3", "1", "25");

        assertThat(actualSolution).isEqualTo("35");
    }

    /**
     * Тестирование метода calculate()
     * Функции
     */
    @ParameterizedTest()
    @ValueSource(strings = {"pow(2+3,1+1)+25"})
    void testCalculatorFunctions(String expression) {
        Calculator calculator = new Calculator(expression);
        MyBigNumber five = new MyBigNumber("5");
        calculator.addFunction("asd", myBigNumber -> myBigNumber.multiply(five));

        String actualSolution = calculator.calculate();

        assertThat(actualSolution).isEqualTo("35");
    }

    /**
     * Тестирование метода calculate()
     * Добавление новых функций
     */
    @ParameterizedTest()
    @ValueSource(strings = {"asd(2+5)-asd(7)+35", "asd(2+5)", "35+asd(2+5)-asd(7)"})
    void testCalculatorAddFunctions(String expression) {
        Calculator calculator = new Calculator(expression);
        MyBigNumber five = new MyBigNumber("5");
        calculator.addFunction("asd", myBigNumber -> myBigNumber.multiply(five));

        String actualSolution = calculator.calculate();

        assertThat(actualSolution).isEqualTo("35");
    }


    /**
     * Тестирование метода calculate()
     * Функция синуса
     */
    @ParameterizedTest()
    @ValueSource(strings = {"sin(2)"})
    void testCalculatorFunctionsSin(String expression) {
        Calculator calculator = new Calculator(expression);

        String actualSolution = calculator.calculate();

        assertThat(actualSolution).startsWith("0.9092");
    }

    /**
     * Тестирование метода calculate()
     * Функция косинуса
     */
    @ParameterizedTest()
    @ValueSource(strings = {"cos(2)"})
    void testCalculatorFunctionsCos(String expression) {
        Calculator calculator = new Calculator(expression);

        String actualSolution = calculator.calculate();

        assertThat(actualSolution).startsWith("-0.4161");
    }

    /**
     * Тестирование метода calculate()
     * Функция квадратного корня
     */
    @ParameterizedTest()
    @ValueSource(strings = {"sqrt(25)"})
    void testCalculatorFunctionsSqrt(String expression) {
        Calculator calculator = new Calculator(expression);

        String actualSolution = calculator.calculate();

        assertThat(actualSolution).startsWith("5");
    }

    /**
     * Тестирование метода calculate()
     * Функция тангенса
     */
    @ParameterizedTest()
    @ValueSource(strings = {"tg(2)"})
    void testCalculatorFunctionsTg(String expression) {
        Calculator calculator = new Calculator(expression);

        String actualSolution = calculator.calculate();

        assertThat(actualSolution).startsWith("-2.1850");
    }

    /**
     * Тестирование метода calculate()
     * Функция котангенса
     */
    @ParameterizedTest()
    @ValueSource(strings = {"ctg(2)"})
    void testCalculatorFunctionsCtg(String expression) {
        Calculator calculator = new Calculator(expression);

        String actualSolution = calculator.calculate();

        assertThat(actualSolution).startsWith("-0.4576");
    }

}