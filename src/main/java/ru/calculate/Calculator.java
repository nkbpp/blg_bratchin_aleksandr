package ru.calculate;

/**
 * #3
 * @author Александр Братчин
 * @version 2.0
 */

public final class Calculator {

    private char[] expression;

    /**
     * Вспомогательный метод вычисляет результат выполнения операции над атрибутами arg1, arg2
     *
     * @return возвращает индекс символа
     */
    private int evaluateExpression(char c, int arg1, int arg2) {
        return switch (c) {
            case '+' -> arg1 + arg2;
            default -> arg1 - arg2;
        };
    }

    public Calculator(String expression) {
        this.expression = expression.toCharArray();//единственное место где использовался сторонний метод класса String
    }

    /**
     * Метод получения результата выполнения выражения
     *
     * @return возвращает int значение
     */
    public int calculate() {
        if (expression.length < 3) {
            throw new IllegalArgumentException("Invalid expression " + expression);
        }

        int start = 0;
        int indexOperator = findIndexOperator(expression, start + 1);

        if (indexOperator == -1) {
            throw new IllegalArgumentException(
                    String.format("Operation sign not found in string: '%s'! Correct operations are '+' or '-'", expression));
        }

        int result = getArg(start, indexOperator);

        for (start = indexOperator, indexOperator = findIndexOperator(expression, start + 2);
             indexOperator != -1; start = indexOperator,
                     indexOperator = findIndexOperator(expression, start + 2)) {
            result = evaluateExpression(expression[start], result, getArg(start + 1, indexOperator));
        }

        result = evaluateExpression(expression[start], result, getArg(start + 1, expression.length));

        return result;
    }

    /**
     * Вспомогательный метод получения первого числа из строки
     * число может быть отрицательным
     *
     * @return возвращает int значение первого числа в выражении
     */
    private int getArg(int start, int end) {
        if (start >= end) {
            throw new IllegalArgumentException(
                    String.format("No numbers found after symbol '%s'!", expression[start - 1])); //TODO тут еще не обработал
        }
        int arg1 = 0;
        for (int i = end - 1, j = 0; i >= start; i--) {
            if (i == start && expression[i] == '-') {
                arg1 *= -1;
            } else {
                arg1 += (getNumber(i) * degreeOfTen(j++));
            }
        }
        return arg1;
    }


    /**
     * Вспомогательный метод вычисления втепеней числа 10
     *
     * @return возвращает int значение 10 в степени degree
     */
    private int degreeOfTen(int degree) {
        int result = 1;
        for (int i = 0; i < degree; i++) {
            result *= 10;
        }
        return result;
    }

    /**
     * Вспомогательный метод перевода символа числа в число
     * если символ не чиcло выпадет исключение IllegalArgumentException
     *
     * @return возвращает int число
     */
    private int getNumber(int index) {
        int number = (expression[index] - '0');
        if (number >= 10 || number < 0) {
            throw new IllegalArgumentException(
                    String.format("Character '%s' is not a number!", expression[index]));
        }
        return number;
    }

    /**
     * Вспомогательный метод поиска символа операции в массиве char начиная с индекса start
     *
     * @return возвращает индекс символа
     */
    private int findIndexOperator(char[] chars, int start) {
        for (int i = start; i < chars.length; i++) {
            switch (chars[i]) {
                case '+', '-' -> {
                    return i;
                }
            }
        }
        return -1;
    }


}
