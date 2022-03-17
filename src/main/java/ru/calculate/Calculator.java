package ru.calculate;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
 * #10 Доработка калькулятор. Добавлена поддержка переменных и функций
 *
 * @author Александр Братчин
 */

public final class Calculator {

    private class MyFunction {
        String name;
        UnaryOperator<MyBigNumber> predicateUnary;
        BinaryOperator<MyBigNumber> predicateBinary;

        public MyFunction(String name, UnaryOperator<MyBigNumber> predicateUnary) {
            this.name = name;
            this.predicateUnary = predicateUnary;
        }

        public MyFunction(String name, BinaryOperator<MyBigNumber> predicateBinary) {
            this.name = name;
            this.predicateBinary = predicateBinary;
        }

        public MyBigNumber f(MyBigNumber myBigNumber) {
            return predicateUnary.apply(myBigNumber);
        }

        public MyBigNumber f(MyBigNumber myBigNumber1, MyBigNumber myBigNumber2) {
            return predicateBinary.apply(myBigNumber1, myBigNumber2);
        }

        public String getName() {
            return name;
        }

    }

    private MyFunction[] functionUnaries;

    {
        functionUnaries = new MyFunction[7];
        functionUnaries[0] = new MyFunction("sqrt", (val) -> {
            MyBigNumber eps = new MyBigNumber("0.0000001");
            MyBigNumber left = new MyBigNumber("0.01");
            MyBigNumber right = new MyBigNumber(val.toString());
            MyBigNumber middle;
            MyBigNumber approx;

            do {
                middle = right.subtract(left).divide(new MyBigNumber("2")).add(left);
                approx = middle.multiply(middle);
                if (approx.compareTo(val) == 1) {
                    right = middle;
                } else {
                    left = middle;
                }

            } while (approx.subtract(val).fabs().compareTo(eps) == 1);

            return middle;
        });
        functionUnaries[1] = new MyFunction("sqr", (val) -> val.multiply(val));
        functionUnaries[2] = new MyFunction("pow", (val1, val2) -> val1.pow(val2));
        functionUnaries[3] = new MyFunction("sin", (x) -> {

            MyBigNumber n = new MyBigNumber("1");
            MyBigNumber an = new MyBigNumber(x.toString());
            MyBigNumber s = new MyBigNumber("0");
            MyBigNumber eps = new MyBigNumber("0.0000001");

            while (an.fabs().compareTo(eps) == 1) {

                s = s.add(an);

                n = n.add(new MyBigNumber("1"));//n++

                MyBigNumber x1 = x.multiply(new MyBigNumber("-1")).multiply(x);

                MyBigNumber x21 = n.multiply(new MyBigNumber("2")).subtract(new MyBigNumber("1"));

                MyBigNumber x22 = n.multiply(new MyBigNumber("2")).subtract(new MyBigNumber("2"));

                MyBigNumber x2 = x1.divide(x21).divide(x22);

                an = an.multiply(x2);

            }
            return s;

        });

        functionUnaries[4] = new MyFunction("cos", (x) -> {

            MyBigNumber n = new MyBigNumber("0");
            MyBigNumber an = new MyBigNumber("1");
            MyBigNumber s = new MyBigNumber("0");
            MyBigNumber eps = new MyBigNumber("0.0000001");

            while (an.fabs().compareTo(eps) == 1) {
                s = s.add(an);

                n = n.add(new MyBigNumber("1"));//n++

                MyBigNumber x1 = x.multiply(new MyBigNumber("-1")).multiply(x);

                MyBigNumber x21 = n.multiply(new MyBigNumber("2")).subtract(new MyBigNumber("1"));

                MyBigNumber x22 = n.multiply(new MyBigNumber("2"));

                MyBigNumber x3 = x1.divide(x21);

                MyBigNumber x4 = x3.divide(x22);
                an = an.multiply(x4);
            }

            return s;
        });

        functionUnaries[5] = new MyFunction("tg", (x) -> (new MyBigNumber(new Calculator("sin(" + x + ")").calculate()))
                .divide(new MyBigNumber(new Calculator("cos(" + x + ")").calculate())));

        functionUnaries[6] = new MyFunction("ctg", (x) -> (new MyBigNumber(new Calculator("cos(" + x + ")").calculate()))
                .divide(new MyBigNumber(new Calculator("sin(" + x + ")").calculate())));
    }

    private void addPlace() {
        MyFunction[] newFunctionUnaries = new MyFunction[functionUnaries.length + 1];
        for (int i = 0; i < functionUnaries.length; i++) {
            newFunctionUnaries[i] = functionUnaries[i];
        }
        functionUnaries = newFunctionUnaries;
    }

    public void addFunction(String name, UnaryOperator<MyBigNumber> predicate) {
        addPlace();
        functionUnaries[functionUnaries.length - 1] = new MyFunction(name, predicate);
    }

    public void addFunction(String name, BinaryOperator<MyBigNumber> predicate) {
        addPlace();
        functionUnaries[functionUnaries.length - 1] = new MyFunction(name, predicate);
    }

    private char[] expression;

    public Calculator(String expression) {
        this.expression = expression.toCharArray();
    }

    /**
     * Метод calculate c параметрами
     *
     * @return возвращает String значение
     */
    public String calculate(String... param) {

        for (int i = 0; i < expression.length; i++) {
            int startParamIndex = i;
            int endParamIndex = 0;
            char c = expression[i];
            int indexParam = -1;

            if (c == 'x' && i + 1 < expression.length && expression[i + 1] > 47 && expression[i + 1] < 58) {

                for (int j = i + 1; j < expression.length && expression[j] > 47 && expression[j] < 58; j++) {
                    indexParam = (indexParam == -1) ? MyBigNumber.getNumber(expression[j]) : indexParam * 10 + MyBigNumber.getNumber(expression[j]);
                    endParamIndex = j;
                }

                MyBigNumber val;
                //нет параметра
                try {
                    val = new MyBigNumber(param[indexParam]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("No param: x" + indexParam);
                }
                char[] thisParam = val.toString().toCharArray();

                expression = addSubstring(expression, thisParam, startParamIndex, endParamIndex + 1);
            }
        }

        return calculate();
    }

    private int indexOf(char[] str, char symbol) {
        for (int i = 0; i < str.length; i++) {
            if (str[i] == symbol) return i;
        }
        return -1;
    }

    private char[] substring(char[] str, int start, int end) {
        char[] substring = new char[end - start];
        for (int i = 0; i < substring.length; i++) {
            substring[i] = str[start++];
        }
        return substring;
    }

    private char[] addSubstring(char[] str1, char[] str2, int start, int end) {
        char[] newExpression = new char[str1.length - (end - start) + str2.length];
        int newExpressionIndex = 0;
        for (int j = 0; j < start; j++) {
            newExpression[newExpressionIndex++] = str1[j];
        }
        for (char c : str2) {
            newExpression[newExpressionIndex++] = c;
        }
        for (int j = end; j < str1.length; j++) {
            newExpression[newExpressionIndex++] = str1[j];
        }
        return newExpression;
    }

    private boolean isExpression(char[] newExpression) {
        boolean isOperator = false;
        for (int j = 0; j < newExpression.length; j++) {
            if (newExpression[j] == '+' || newExpression[j] == '*' || newExpression[j] == '/' || (newExpression[j] == '-' && j != 0)) {
                isOperator = true;
                break;
            }
        }
        return isOperator;
    }

    private MyBigNumber subexpressionEvaluation(char[] newExpression) {
        //вычисление выражений
        if (isExpression(newExpression)) {
            return new MyBigNumber(new Calculator(new String(newExpression)).calculate());
        } else {
            return new MyBigNumber(new String(newExpression));
        }

    }

    /**
     * Метод получения результата выполнения выражения
     *
     * @return возвращает String значение
     */
    public String calculate() {
        if (expression.length < 3) {
            throw new IllegalArgumentException("Not enough characters to calculate"); //недостаточно символов для вычисления
        }
        MyBigNumber arg = new MyBigNumber("0");
        boolean startNewNumber = true;
        boolean positiveNumber = true;
        boolean endBrackets = false;
        int fractionalPart = -1;
        int colBrackets = 0;


        StackBigNumber stackBigNumber = new StackBigNumber(expression.length - 1);
        StackChar stackChar = new StackChar(expression.length - 1);
        for (int i = 0; i < expression.length; i++) {
            Operator tekOperator = Operator.getOperator(expression[i]);
            switch (expression[i]) {
                case '.' -> {
                    if (fractionalPart == -1 && i > 0 && i < expression.length - 1 && !startNewNumber) {
                        fractionalPart++;
                    } else {
                        throw new IllegalArgumentException("Character '.' in the wrong place");
                    }
                }
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {

                    if (startNewNumber) {
                        arg = new MyBigNumber(new char[]{expression[i]});
                        if (!positiveNumber) {
                            arg = arg.multiply(new MyBigNumber("-1"));
                            positiveNumber = true;
                        }
                    } else {
                        if (MyBigNumber.clearZero(arg.unscaledValue).length == 0) {
                            arg = new MyBigNumber(new char[]{expression[i]});
                        } else {
                            arg = arg.multiply(new MyBigNumber("10")).add(new MyBigNumber(new char[]{expression[i]}));
                        }

                    }
                    startNewNumber = false;
                    if (fractionalPart > -1) {
                        fractionalPart++;
                    }
                }
                case '+', '-', '*', '/' -> {
                    if (i + 1 == expression.length) {
                        throw new IllegalArgumentException("The string does not end with a number"); //выражение не должно заканчиваться знаком
                    }
                    if (startNewNumber) {
                        if (tekOperator == Operator.Minus) {
                            if (!positiveNumber) {
                                throw new IllegalArgumentException("Wrong order of operators!"); //два оператора подряд
                            }
                            positiveNumber = false;
                        } else {
                            throw new IllegalArgumentException("Wrong order of operators"); //два оператора подряд
                        }
                    } else {
                        if (!endBrackets) {
                            arg.point = fractionalPart;
                            stackBigNumber.push(arg);
                        }
                        // стек операторов пуст или приоритет текущего оператора ниже
                        while (!stackChar.isEmpty() && tekOperator.getPriority() < Operator.getOperator(stackChar.peek()).getPriority()) {
                            MyBigNumber val1 = stackBigNumber.pop();
                            MyBigNumber val2 = stackBigNumber.pop();
                            stackBigNumber.push(evaluateExpression(stackChar.pop(), val2, val1));
                        }

                        stackChar.push(tekOperator.getOperator());
                        startNewNumber = true;
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
                    if (stackBigNumber.isEmpty() || stackChar.isEmpty() || colBrackets < 0) {//нарушен порядок скобок
                        throw new IllegalArgumentException("Parentheses out of order");
                    }
                    arg.point = fractionalPart;
                    stackBigNumber.push(arg);
                    while (stackChar.peek() != '(') {
                        MyBigNumber val1 = stackBigNumber.pop();
                        MyBigNumber val2 = stackBigNumber.pop();
                        stackBigNumber.push(evaluateExpression(stackChar.pop(), val2, val1));
                    }
                    stackChar.pop();
                    startNewNumber = false;
                    endBrackets = true;
                }
                default -> {
                    //алфавит Обработка функции
                    if (expression[i] > 96 && expression[i] < 123) {
                        int indexNameFunctionEnd = indexOf(expression, '(');
                        String nameFunction = new String(substring(expression, i, indexNameFunctionEnd));

                        int indexComma = -1;

                        int indexExpressionEnd = indexNameFunctionEnd + 1;
                        for (int colBrakers = 1; colBrakers != 0; indexExpressionEnd++) {
                            if (expression[indexExpressionEnd] == '(') {
                                colBrakers++;
                            }
                            if (expression[indexExpressionEnd] == ')') {
                                colBrakers--;
                            }
                            if (expression[indexExpressionEnd] == ',' && colBrakers == 1) {
                                indexComma = indexExpressionEnd;
                            }
                        }

                        if (indexComma == -1) { //один параметр

                            MyBigNumber myBigNumber = subexpressionEvaluation(substring(expression, indexNameFunctionEnd + 1, indexExpressionEnd - 1));

                            //выполнить функцию и вставить результат
                            for (MyFunction functionUnary : functionUnaries) {
                                if (functionUnary.getName().equals(nameFunction)) {
                                    MyBigNumber answer = functionUnary.f(myBigNumber);
                                    expression = addSubstring(expression, answer.toString().toCharArray(), i, indexExpressionEnd);
                                    indexExpressionEnd = answer.toString().length();
                                    arg = answer;
                                    break;
                                }
                            }

                        } else { //два параметра

                            //выражение внутри скобок до запятой, вычисление выражений
                            MyBigNumber myBigNumber1 = subexpressionEvaluation(substring(expression, indexNameFunctionEnd + 1, indexComma));
                            MyBigNumber myBigNumber2 = subexpressionEvaluation(substring(expression, indexComma + 1, indexExpressionEnd - 1));

                            //выполнить функцию и вставить результат
                            for (MyFunction functionUnary : functionUnaries) {
                                if (functionUnary.getName().equals(nameFunction)) {
                                    MyBigNumber answer = functionUnary.f(myBigNumber1, myBigNumber2);
                                    expression = addSubstring(expression, answer.toString().toCharArray(), i, indexExpressionEnd);
                                    indexExpressionEnd = answer.toString().length();
                                    arg = answer;

                                    break;
                                }
                            }
                        }

                        i = i + indexExpressionEnd - 1;
                        if (!isExpression(expression)) {
                            return new MyBigNumber(expression).toString();
                        }
                        startNewNumber = false;

                    } else {
                        throw new IllegalArgumentException("Unexpected value: " + expression[i]); //неизвестный символ
                    }
                }
            }
        }
        if (colBrackets != 0) {//нарушен порядок скобок
            throw new IllegalArgumentException("Parentheses out of order");
        }
        if (!stackChar.isEmpty()) {// если еще есть операторы
            arg.point = fractionalPart;
            stackBigNumber.push(arg);
            stackChar.reverse();
            stackBigNumber.reverse();
            MyBigNumber result = evaluateExpression(stackChar.pop(), stackBigNumber.pop(), stackBigNumber.pop());
            while (!stackChar.isEmpty()) {
                result = evaluateExpression(stackChar.pop(), result, stackBigNumber.pop());
            }
            return result.toString();
        } else {
            return stackBigNumber.pop().toString();
        }

    }

    /**
     * Вспомогательный метод вычисляет результат выполнения операции над атрибутами arg1, arg2
     */
    private MyBigNumber evaluateExpression(char c, MyBigNumber arg1, MyBigNumber arg2) {
        return switch (c) {
            case '+' -> new MyBigNumber(arg1.add(arg2).toString());
            case '-' -> new MyBigNumber(arg1.subtract(arg2).toString());
            case '/' -> new MyBigNumber(arg1.divide(arg2).toString());
            case '*' -> new MyBigNumber(arg1.multiply(arg2).toString());
            default -> arg2;
        };
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

        final char operator;
        final int priority;

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

    /**
     * Стек double для хранения чисел
     */
    private class StackBigNumber {
        private MyBigNumber[] stackArr;
        private int top;

        public StackBigNumber(int size) {
            stackArr = new MyBigNumber[size];
            top = -1;
        }

        public void push(MyBigNumber value) {
            stackArr[++top] = value;
        }

        public MyBigNumber pop() {
            return stackArr[top--];
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public void reverse() {
            MyBigNumber[] newStackArr = new MyBigNumber[stackArr.length];
            for (int i = top, j = 0; i >= 0; i--) {
                newStackArr[j++] = stackArr[i];
            }
            stackArr = newStackArr;
        }

    }
}


/**
 * Для хранения чисел неограниченной длинны
 */
class MyBigNumber implements Comparable<MyBigNumber> {
    int scale; //для хранения знака

    char[] unscaledValue; //для хранения чисел

    int point = -1;

    public MyBigNumber(char[] str) {
        if (str.length == 0) {
            throw new NullPointerException();//строка пуста
        }

        if (str.length > 1) { //определяем знак числа
            scale = (str[0] == '-') ? -1 : 1;
        } else {
            scale = 1;
            if (str.length == 1 && str[0] == '0') { //число 0
                scale = 0;
            }
        }

        for (int i = str.length - 1, n = 0; i >= 0; i--, n++) {
            if (str[i] == '.') {
                point = n;
            }
        }

        int n = str.length;
        n = (scale == -1) ? n - 1 : n;
        n = (point == -1) ? n : n - 1;
        unscaledValue = new char[n];
        for (int i = 0, j = 0; i < str.length; i++) {
            char c = str[i];
            if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
                unscaledValue[j++] = c;
            }
        }
    }

    private MyBigNumber(int scale, char[] str) {
        this(str);
        this.scale = scale;
    }

    private MyBigNumber(int scale, char[] str, int point) {
        this(scale, str);
        this.point = point + 1;
    }


    public MyBigNumber(String s) {
        this(s.toCharArray());
    }


    @Override
    public String toString() {
        int maxLength = unscaledValue.length;
        if (point != -1) {
            if (maxLength - point - 1 >= 0) {
                maxLength = maxLength + 1;
            } else if (maxLength - point - 1 < 0) {
                maxLength = maxLength + (point + 2 - maxLength);
            }
        }
        maxLength = scale == -1 ? maxLength + 1 : maxLength;

        char[] result = new char[maxLength];
        if (scale == -1) {
            result[0] = '-';
        }
        if (point != -1) {
            result[maxLength - point - 1] = '.';
        }

        for (int i = maxLength - 1, j = unscaledValue.length - 1; i >= 0; i--) {
            if (result[i] == '-') {
                break;
            }
            if (result[i] == '.') {
                i--;
            }
            if (j >= 0) {
                result[i] = unscaledValue[j--];
            } else {
                result[i] = '0';
            }
        }

        if (point != -1) {
            result = reverse(clearZero(reverse(result)));
            if (result[result.length - 1] == '.') {
                char[] resultWithoutPoint = new char[result.length - 1];
                for (int i = 0; i < resultWithoutPoint.length; i++) {
                    resultWithoutPoint[i] = result[i];
                }
                return new String(resultWithoutPoint);
            }
        }

        return new String(result);
    }

    /**
     * Сложение
     */
    private char[] sum(char[] arg1, char[] arg2) { //когда знаки одинаковы сложение
        char[] resultArr = new char[max(arg1.length, arg2.length)];

        int over = 0;
        char[] reverseArg1 = reverse(arg1);
        char[] reverseArg2 = reverse(arg2);
        for (int i = 0; i < resultArr.length; i++) {
            int val1 = i < reverseArg1.length ? getNumber(reverseArg1[i]) : 0;
            int val2 = i < reverseArg2.length ? getNumber(reverseArg2[i]) : 0;

            int result = val1 + over + val2;
            if (result >= 10) {
                over = result / 10;
                result %= 10;
            } else {
                over = 0;
            }
            resultArr[i] = getChar(result);
        }
        return over > 0 ? join(new char[]{getChar(over)}, reverse(resultArr)) : reverse(resultArr);
    }

    /**
     * Максимум
     */
    private int max(int arg1, int arg2) {
        return arg1 > arg2 ? arg1 : arg2;
    }

    /**
     * Модуль
     */
    public MyBigNumber fabs() {
        return new MyBigNumber(1, this.unscaledValue, this.point);
    }

    /**
     * вычитание
     */
    private char[] subtract(char[] big, char[] little) {
        int bigIndex = big.length;
        char[] resultArr = new char[bigIndex];

        char[] reverseBig = reverse(big);
        char[] reverseLittle = reverse(little);

        int over = 0;

        for (int i = 0; i < resultArr.length; i++) {
            int val1 = i < reverseBig.length ? getNumber(reverseBig[i]) + over : 0;
            int val2 = i < reverseLittle.length ? getNumber(reverseLittle[i]) : 0;

            int result = val1 - val2;
            if (result < 0) {
                result += 10;
                over = -1;
            } else {
                over = 0;
            }
            resultArr[i] = getChar(result);
        }

        return clearZero(reverse(resultArr));
    }

    /**
     * Приводит переменные к целым числам и возвращает максимальный множитель 10
     */
    private int castingToAnInteger(MyBigNumber val1, MyBigNumber val2) {
        int pointMax = max(val1.point, val2.point);
        if (pointMax != -1) {
            multiplyTen(val1, pointMax);
            multiplyTen(val2, pointMax);
        }
        return pointMax;
    }

    /**
     * умножить на 10 pointMax раз
     */
    private void multiplyTen(MyBigNumber val,
                             int pointMax) {
        for (int i = 0; i < pointMax; i++) {
            val.unscaledValue = new MyBigNumber(val.multiply(new MyBigNumber("10")).toString()).unscaledValue;
            if (val.point > -1) {
                val.point--;
            }
        }
        val.unscaledValue = clearZero(val.unscaledValue);
        val.point = -1;
    }

    /**
     * Сложение
     */
    public MyBigNumber add(MyBigNumber myBigNumber) {
        if (myBigNumber.scale == 0)
            return this;
        if (scale == 0)
            return myBigNumber;

        //точка
        MyBigNumber myBigNumber1 = new MyBigNumber(this.toString());
        MyBigNumber myBigNumber2 = new MyBigNumber(myBigNumber.toString());
        int pointMax = castingToAnInteger(myBigNumber1, myBigNumber2);

        if (myBigNumber2.scale == myBigNumber1.scale) {
            return new MyBigNumber(
                    myBigNumber1.scale,
                    sum(myBigNumber2.getUnscaledValue(), myBigNumber1.unscaledValue),
                    pointMax - 1
            );
        } else {
            int cmp = myBigNumber1.compareMagnitude(myBigNumber2);
            if (cmp == 0)
                return new MyBigNumber("0");
            char[] resultMag = (cmp > 0 ? subtract(myBigNumber1.unscaledValue, myBigNumber2.getUnscaledValue())
                    : subtract(myBigNumber2.getUnscaledValue(), myBigNumber1.unscaledValue));
            return new MyBigNumber(cmp < 0 ? myBigNumber2.getScale() : myBigNumber1.scale, resultMag, pointMax - 1);
        }
    }

    /**
     * Вычитание
     */
    public MyBigNumber subtract(MyBigNumber val) {
        if (val.scale == 0)
            return this;
        if (scale == 0) {
            return val.multiply(new MyBigNumber("-1"));
        }

        //точка
        MyBigNumber myBigNumber1 = new MyBigNumber(this.toString());
        MyBigNumber myBigNumber2 = new MyBigNumber(val.toString());
        int pointMax = castingToAnInteger(myBigNumber1, myBigNumber2);

        if (myBigNumber2.scale != myBigNumber1.scale) {
            return new MyBigNumber(
                    myBigNumber1.scale,
                    sum(myBigNumber1.unscaledValue, myBigNumber2.getUnscaledValue()),
                    pointMax - 1
            );
        }

        int cmp = myBigNumber1.compareMagnitude(myBigNumber2);
        if (cmp == 0) {
            return new MyBigNumber("0");
        }
        char[] resultMag = (cmp > 0 ? subtract(myBigNumber1.unscaledValue, myBigNumber2.getUnscaledValue())
                : subtract(myBigNumber2.getUnscaledValue(), myBigNumber1.unscaledValue));
        return new MyBigNumber(cmp > 0 ? 1 : -1, resultMag, pointMax - 1);
    }


    /**
     * Умножение
     * this * val
     */
    //
    public MyBigNumber multiply(MyBigNumber val) {

        if (val.scale == 0 || this.scale == 0) {
            return new MyBigNumber("0");
        }

        int point = -1;
        if (this.point != -1 && val.point == -1) {
            point = this.point;
        }
        if (this.point == -1 && val.point != -1) {
            point = val.point;
        }
        if (this.point != -1 && val.point != -1) {
            point = val.point + this.point;
        }

        return new MyBigNumber(this.scale == val.scale ? 1 : -1, multiply(this.unscaledValue, val.unscaledValue), point - 1);
    }

    /**
     * Умножение
     */
    public char[] multiply(char[] num1, char[] num2) {

        // Выделите пробел для хранения результата операции.
        // Длинное число num1 * Длинное число num2, результат не будет превышать длинную num1 + num2
        int[] result = new int[num1.length + num2.length];

        // Независимо от проблемы переноса, согласно операции вертикального умножения,
        // i-й бит num1 умножается на j-й бит num2, и результат должен быть сохранен в
        //позиция i + j
        for (int i = 0; i < num1.length; i++) {
            for (int j = 0; j < num2.length; j++) {
                result[i + j + 1] += (getNumber(num1[i]) * getNumber(num2[j]));
            }
        }

        // Ручка переноса в одиночку
        char[] result2 = new char[result.length];
        for (int k = result.length - 1; k > 0; k--) {
            if (result[k] >= 10) {
                result[k - 1] += result[k] / 10;
                result[k] %= 10;
            }
            result2[k] = getChar(result[k]);
        }
        result2[0] = getChar(result[0]);

        return clearZero(result2);
    }

    /**
     * Возведение в степень
     */
    public MyBigNumber pow(MyBigNumber val) {
        if (this.scale == 0 || val.scale == 0) {
            return new MyBigNumber("1");
        }
        return new MyBigNumber(1, multiply(this.unscaledValue, val.unscaledValue));
    }

    /**
     * Деление
     */
    public MyBigNumber divide(MyBigNumber val) {
        //Деление на ноль
        if (val.scale == 0) { //делитель равен 0
            throw new NullPointerException();
        }

        if (this.scale == 0) { //делимое равно 0
            return new MyBigNumber("0");
        }

        int resultScale = this.scale == val.scale ? 1 : -1;

        MyBigNumber myBigNumber1 = new MyBigNumber(this.toString());
        myBigNumber1.scale = 1;
        MyBigNumber myBigNumber2 = new MyBigNumber(val.toString());
        myBigNumber2.scale = 1;
        castingToAnInteger(myBigNumber1, myBigNumber2);

        char[] num1 = myBigNumber1.unscaledValue;
        char[] num2 = myBigNumber2.unscaledValue;
        // Выделите пробел для хранения результата операции.
        // Длинное число num1 * Длинное число num2, результат не будет превышать длинную num1 + num2

        MyBigNumber result = new MyBigNumber("0");
        int indexPoint = 0;

        int indexNum1 = 0;
        MyBigNumber dividend = new MyBigNumber("0");

        while ((indexNum1 < num1.length || compareMagnitude(dividend.getUnscaledValue(), new char[]{'0'}) != 0) && indexPoint < 9) {
            //бесплатно
            if (indexNum1 >= num1.length) {
                dividend = new MyBigNumber(multiply(dividend.getUnscaledValue(), new char[]{'1', '0'}));
                indexPoint++;
            } else {
                if (dividend.compareTo(new MyBigNumber("0")) == 0) { //первое число
                    dividend = new MyBigNumber(new char[]{num1[indexNum1++]});
                    while (indexNum1 < num1.length) {
                        dividend = new MyBigNumber(
                                sum(multiply(dividend.getUnscaledValue(), new char[]{'1', '0'}), new char[]{num1[indexNum1++]}));
                    }
                } else {
                    dividend = new MyBigNumber(
                            sum(multiply(dividend.getUnscaledValue(), new char[]{'1', '0'}), new char[]{num1[indexNum1++]}));
                }
            }

            //платно
            while (compareMagnitude(dividend.getUnscaledValue(), num2) < 0) {
                if (indexNum1 >= num1.length) {
                    dividend = new MyBigNumber(multiply(dividend.getUnscaledValue(), new char[]{'1', '0'}));
                    result = result.multiply(new MyBigNumber("10"));
                } else {
                    dividend = new MyBigNumber(
                            sum(multiply(dividend.getUnscaledValue(), new char[]{'1', '0'}), new char[]{num1[indexNum1++]}));
                }
                indexPoint++;
            }

            MyBigNumber factor = getFactor(dividend, myBigNumber2);

            dividend = dividend.subtract(myBigNumber2.multiply(factor));

            if (result.equals(new MyBigNumber("0"))) {
                result.add(factor);
            } else {
                result = new MyBigNumber(
                        sum(multiply(result.getUnscaledValue(), new char[]{'1', '0'}), factor.unscaledValue));

                result.point = indexPoint > 0 ? indexPoint : -1;
            }
        }

        result.scale = resultScale;
        return result;
    }

    /**
     * Рекурсивный поиск множителя
     */
    private MyBigNumber getFactor(MyBigNumber dividend, MyBigNumber divide) {
        MyBigNumber factor = new MyBigNumber("1");

        while (compareMagnitude(divide.pow(factor.pow(new MyBigNumber("2"))).getUnscaledValue(), dividend.unscaledValue) < 0) {
            factor = factor.pow(new MyBigNumber("2"));
        }


        dividend = dividend.subtract(divide.multiply(factor));

        if (dividend.compareTo(divide) < 0) {
            return factor;
        }

        return factor.add(getFactor(dividend, divide));
    }

    /**
     * Удаление ведущих нулей
     */
    public static char[] clearZero(char[] arr) {
        int colZero = 0;
        for (char c : arr) {
            if (c != '0') {
                break;
            }
            colZero++;
        }
        char[] zeroArr = new char[arr.length - colZero];
        for (int i = colZero, j = 0; i < arr.length; i++) {
            zeroArr[j++] = arr[i];
        }
        return zeroArr;
    }

    /**
     * Обратный порядок
     */
    private char[] reverse(char[] arr) {
        char[] reverseArr = new char[arr.length];
        for (int i = arr.length - 1, j = 0; i >= 0; i--) {
            reverseArr[j++] = arr[i];
        }
        return reverseArr;
    }

    /**
     * Объединить 2 массива
     */
    private char[] join(char[] chars1, char[] chars2) {
        char[] newChars = new char[chars1.length + chars2.length];
        int j = 0;
        for (char c : chars1) {
            newChars[j++] = c;
        }
        for (char c : chars2) {
            newChars[j++] = c;
        }
        return newChars;
    }

    //Get and Set
    public char[] getUnscaledValue() {
        return unscaledValue;
    }

    public int getScale() {
        return scale;
    }

    /**
     * 1, если вызывающий объект больше объекта, переданного в качестве параметра;
     * -1, если вызывающий объект меньше объекта, переданного в качестве параметра;
     * 0, если объекты равны.
     */
    @Override
    public int compareTo(MyBigNumber val) {
        if (scale == val.scale) {
            return switch (scale) {
                case 1 -> compareMagnitude(val);
                case -1 -> val.compareMagnitude(this);
                default -> 0;
            };
        }
        return scale > val.scale ? 1 : -1;
    }

    //сравнение по модулю
    private int compareMagnitude(MyBigNumber val) {
        MyBigNumber myBigNumber1 = new MyBigNumber(this.toString());
        MyBigNumber myBigNumber2 = new MyBigNumber(val.toString());
        castingToAnInteger(myBigNumber1, myBigNumber2);
        return compareMagnitude(myBigNumber1.unscaledValue, myBigNumber2.unscaledValue);
    }

    //сравнение по модулю
    private int compareMagnitude(char[] arg1, char[] arg2) {
        int len1 = arg1.length;
        int len2 = arg2.length;
        if (len1 < len2)
            return -1;
        if (len1 > len2)
            return 1;
        for (int i = 0; i < arg1.length; i++) {
            if (arg1[i] != arg2[i]) {
                return arg1[i] > arg2[i] ? 1 : -1;
            }
        }
        return 0;
    }

    static int getNumber(int val) {
        return (val - '0');
    }

    static char getChar(int val) {
        return (char) (val + '0');
    }

}