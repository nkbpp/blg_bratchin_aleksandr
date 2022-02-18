package ru.calculate;

/**
 * #3 Доработка калькулятор (количество слагаемых неограничено)
 *
 * @author Александр Братчин
 */

public final class Calculator {

    private char[] expression;

    public Calculator(String expression) {
        this.expression = expression.toCharArray();
    }

    /**
     * Метод получения результата выполнения выражения
     *
     * @return возвращает int значение
     */
    public int calculate() {
        if (expression.length < 3) {
            throw new IllegalArgumentException("Not enough characters to calculate"); //недостаточно символов для вычисления
        }
        int result = 0;
        int arg = 0;
        char operator = '#';
        boolean newChislo = true;
        boolean positiveNumber = true;
        for (int i = 0; i < expression.length; i++) {
            switch (expression[i]) {
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                    if (newChislo) {
                        arg = getNumber(i);
                        if (!positiveNumber) {
                            arg *= -1;
                            positiveNumber = true;
                        }
                    } else {
                        arg = arg * 10 + getNumber(i);
                    }
                    newChislo = false;
                }
                case '+', '-' -> {
                    if (i + 1 == expression.length) {
                        throw new IllegalArgumentException("The string does not end with a number"); //выражение не должно заканчиваться знаком
                    }
                    if (newChislo) {
                        if (expression[i] == '-') {
                            if (!positiveNumber) {
                                throw new IllegalArgumentException("Wrong order of operators!"); //два оператора подряд
                            }
                            positiveNumber = false;
                        } else {
                            throw new IllegalArgumentException("Wrong order of operators"); //два оператора подряд
                        }
                    } else {
                        result = evaluateExpression(operator, result, arg);
                        operator = expression[i];
                        newChislo = true;
                        positiveNumber = true;
                    }
                }
                default -> throw new IllegalArgumentException("Unexpected value: " + expression[i]); //неизвестный символ
            }
        }
        return evaluateExpression(operator, result, arg);
    }


    /**
     * Вспомогательный метод перевода символа числа в число
     *
     * @return возвращает int число
     */
    private int getNumber(int index) {
        return (expression[index] - '0');
    }

    /**
     * Вспомогательный метод вычисляет результат выполнения операции над атрибутами arg1, arg2
     *
     * @return возвращает индекс символа
     */
    private int evaluateExpression(char c, int arg1, int arg2) {
        return switch (c) {
            case '+' -> arg1 + arg2;
            case '-' -> arg1 - arg2;
            default -> arg2;
        };
    }

}
