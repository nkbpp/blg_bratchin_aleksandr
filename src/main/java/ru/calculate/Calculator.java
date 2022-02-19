package ru.calculate;

/**
 * #4 Доработка калькулятор добавление операций деления и умножения (Алгоритм Дейкстра)
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
        int arg = 0;
        boolean newChislo = true;
        boolean positiveNumber = true;

        StackInt stackInt = new StackInt(expression.length - 1);
        StackChar stackChar = new StackChar(expression.length - 1);
        for (int i = 0; i < expression.length; i++) {
            Operator tekOperator = Operator.getOperator(expression[i]);
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
                case '+', '-', '*', '/' -> {
                    if (i + 1 == expression.length) {
                        throw new IllegalArgumentException("The string does not end with a number"); //выражение не должно заканчиваться знаком
                    }
                    if (newChislo) {
                        if (tekOperator == Operator.Minus) {
                            if (!positiveNumber) {
                                throw new IllegalArgumentException("Wrong order of operators!"); //два оператора подряд
                            }
                            positiveNumber = false;
                        } else {
                            throw new IllegalArgumentException("Wrong order of operators"); //два оператора подряд
                        }
                    } else {
                        int result = arg;

                        if (!stackChar.isEmpty() && tekOperator.getPriority() < Operator.getOperator(stackChar.peek()).getPriority()) { // стек операторов пуст или приоритет текущего оператора ниже или равен
                            stackInt.push(arg);
                            while (!stackChar.isEmpty() && tekOperator.getPriority() < Operator.getOperator(stackChar.peek()).getPriority()) {
                                int val1 = stackInt.pop();
                                int val2 = stackInt.pop();
                                result = evaluateExpression(stackChar.pop(), val2, val1);
                            }
                        }
                        stackInt.push(result);
                        stackChar.push(tekOperator.getOperator());
                        newChislo = true;
                        positiveNumber = true;
                    }
                }
                default -> throw new IllegalArgumentException("Unexpected value: " + expression[i]); //неизвестный символ
            }
        }

        int result = evaluateExpression(stackChar.pop(), stackInt.pop(), arg);
        while (!stackChar.isEmpty()) {
            result = evaluateExpression(stackChar.pop(), result, stackInt.pop());
        }
        return result;
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
            case '/' -> arg1 / arg2;
            case '*' -> arg1 * arg2;
            default -> arg2;
        };
    }

    /**
     * Стек интов для хранения чисел
     */
    private class StackInt {
        private int[] stackArr;
        private int top;

        public StackInt(int size) {
            stackArr = new int[size];
            top = -1;
        }

        public void push(int value) {
            stackArr[++top] = value;
        }

        public int pop() {
            return stackArr[top--];
        }

    }

    /**
     * Стек char для хранения операций
     */
    private class StackChar {
        private final char[] stackArr;
        private int top;

        public StackChar(int size) {
            stackArr = new char[size];
            top = -1;
        }

        public void push(char value) {
            stackArr[++top] = value;
        }

        public char pop() {
            return stackArr[top--];
        }

        public char peek() {
            return stackArr[top];
        }

        public boolean isEmpty() {
            return top == -1;
        }
    }

    /**
     * Enum - нужен в основном для получения приоритета операции
     */
    private enum Operator {
        Plus('+', 1),
        Minus('-', 1),
        Divide('/', 2),
        Multiply('*', 2),
        Non('#', -1);

        char operator;
        int priority;

        Operator(char operator, int priority) {
            this.operator = operator;
            this.priority = priority;
        }

        public char getOperator() {
            return operator;
        }

        public int getPriority() {
            return priority;
        }

        static Operator getOperator(char val) {
            return switch (val) {
                case '+' -> Plus;
                case '-' -> Minus;
                case '/' -> Divide;
                case '*' -> Multiply;
                default -> Non;
            };
        }
    }

}
