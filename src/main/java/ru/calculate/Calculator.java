package ru.calculate;

/**
 * #8 Доработка калькулятор. Добавление скобок
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
    public double calculate() {
        if (expression.length < 3) {
            throw new IllegalArgumentException("Not enough characters to calculate"); //недостаточно символов для вычисления
        }
        double arg = 0;
        boolean newChislo = true;
        boolean positiveNumber = true;
        boolean endBrackets = false;
        int fractionalPart = -1;
        int colBrackets = 0;


        StackDouble stackDouble = new StackDouble(expression.length - 1);
        StackChar stackChar = new StackChar(expression.length - 1);
        for (int i = 0; i < expression.length; i++) {
            Operator tekOperator = Operator.getOperator(expression[i]);
            switch (expression[i]) {
                case '.' -> {
                    if (fractionalPart == -1 && i > 0 && i < expression.length - 1 && !newChislo) {
                        fractionalPart++;
                    } else {
                        throw new IllegalArgumentException("Character '.' in the wrong place");
                    }
                }
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
                    if (fractionalPart > -1) {
                        fractionalPart++;
                    }
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
                        if(!endBrackets){
                            stackDouble.push(insertAComma(arg, fractionalPart));//не добавлять после закрывающейся скобки
                        }
                        // стек операторов пуст или приоритет текущего оператора ниже
                        while (!stackChar.isEmpty() && tekOperator.getPriority() < Operator.getOperator(stackChar.peek()).getPriority()) {
                            double val1 = stackDouble.pop();
                            double val2 = stackDouble.pop();
                            stackDouble.push(evaluateExpression(stackChar.pop(), val2, val1));
                        }

                        stackChar.push(tekOperator.getOperator());
                        newChislo = true;
                        positiveNumber = true;
                        fractionalPart = -1;
                        endBrackets = false;
                    }
                }
                case '(' -> {
                    stackChar.push(tekOperator.getOperator());
                    colBrackets++;
                }
                case ')' -> {
                    colBrackets--;
                    if (stackDouble.isEmpty() || stackChar.isEmpty() || colBrackets < 0) {//нарушен порядок скобок
                        throw new IllegalArgumentException("Parentheses out of order");
                    }
                    stackDouble.push(insertAComma(arg, fractionalPart));
                    while (stackChar.peek() != '(') {
                        double val1 = stackDouble.pop();
                        double val2 = stackDouble.pop();
                        stackDouble.push(evaluateExpression(stackChar.pop(), val2, val1));
                    }
                    stackChar.pop();
                    newChislo = false;
                    endBrackets = true;
                }
                default -> throw new IllegalArgumentException("Unexpected value: " + expression[i]); //неизвестный символ
            }
        }
        if (colBrackets != 0) {//нарушен порядок скобок
            throw new IllegalArgumentException("Parentheses out of order");
        }
        if (!stackChar.isEmpty()) {// если еще есть операторы
            stackDouble.push(insertAComma(arg, fractionalPart));
            stackChar.reverse();
            stackDouble.reverse();
            double result = evaluateExpression(stackChar.pop(), stackDouble.pop(), stackDouble.pop());
            while (!stackChar.isEmpty()) {
                result = evaluateExpression(stackChar.pop(), result, stackDouble.pop());
            }
            return result;
        } else {
            return stackDouble.pop();
        }

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
     * Вспомогательный метод добавляет запятую
     *
     * @return возвращает val деленное на 10 в степени fractionalPart
     */
    private double insertAComma(double val, int fractionalPart) {
        return (fractionalPart == -1) ? val : val / degreeOfTen(fractionalPart);
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
     */
    private double evaluateExpression(char c, double arg1, double arg2) {
        return switch (c) {
            case '+' -> arg1 + arg2;
            case '-' -> arg1 - arg2;
            case '/' -> arg1 / arg2;
            case '*' -> arg1 * arg2;
            default -> arg2;
        };
    }

    /**
     * Стек double для хранения чисел
     */
    private class StackDouble {
        private double[] stackArr;
        private int top;

        public StackDouble(int size) {
            stackArr = new double[size];
            top = -1;
        }

        public void push(double value) {
            stackArr[++top] = value;
        }

        public double pop() {
            return stackArr[top--];
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public void reverse() {
            double[] newStackArr = new double[stackArr.length];
            for (int i = top, j = 0; i >= 0; i--) {
                newStackArr[j++] = stackArr[i];
            }
            stackArr = newStackArr;
        }

    }

    /**
     * Стек char для хранения операций
     */
    private class StackChar {
        private char[] stackArr;
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

        public void reverse() {
            char[] newStackArr = new char[stackArr.length];
            for (int i = top, j = 0; i >= 0; i--) {
                newStackArr[j++] = stackArr[i];
            }
            stackArr = newStackArr;
        }
    }

    /**
     * Enum - нужен в основном для получения приоритета операции
     */
    private enum Operator {
        OpeningBracket('(', 0),
        CloseBracket(')', 0),
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
                case '(' -> OpeningBracket;
                case ')' -> CloseBracket;
                case '+' -> Plus;
                case '-' -> Minus;
                case '/' -> Divide;
                case '*' -> Multiply;
                default -> Non;
            };
        }
    }

}
