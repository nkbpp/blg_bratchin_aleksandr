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
/*    public double calculate() {
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

    }*/

    /**
     * Метод получения результата выполнения выражения
     *
     * @return возвращает int значение
     */
    public String calculate() {
        if (expression.length < 3) {
            throw new IllegalArgumentException("Not enough characters to calculate"); //недостаточно символов для вычисления
        }
        MyBigNumber arg = new MyBigNumber(0, new char[] {'0'});
        boolean newChislo = true;
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
                    if (fractionalPart == -1 && i > 0 && i < expression.length - 1 && !newChislo) {
                        fractionalPart++;
                        //arg.unscaledValue = MyBigNumber.clearZero(arg.unscaledValue);
                    } else {
                        throw new IllegalArgumentException("Character '.' in the wrong place");
                    }
                }
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {

                    if (newChislo) {
                        arg = new MyBigNumber(new char[]{expression[i]});
                        if (!positiveNumber) {
                            arg = arg.multiply(new MyBigNumber(new char[]{'-','1'}));
                            positiveNumber = true;
                        }
                    } else {
                        if(MyBigNumber.clearZero(arg.unscaledValue).length == 0){
                            arg = new MyBigNumber(new char[]{expression[i]});
                        } else {
                            arg = arg.multiply(new MyBigNumber(new char[]{'1','0'})).add(new MyBigNumber(new char[]{expression[i]}));
                        }

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
                            arg.point = fractionalPart;
                            stackBigNumber.push(arg);
                            //stackDouble.push(insertAComma(arg, fractionalPart));//не добавлять после закрывающейся скобки
                        }
                        // стек операторов пуст или приоритет текущего оператора ниже
                        while (!stackChar.isEmpty() && tekOperator.getPriority() < Operator.getOperator(stackChar.peek()).getPriority()) {
                            MyBigNumber val1 = stackBigNumber.pop();
                            MyBigNumber val2 = stackBigNumber.pop();
                            stackBigNumber.push(evaluateExpression(stackChar.pop(), val2, val1));
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
                    if (stackBigNumber.isEmpty() || stackChar.isEmpty() || colBrackets < 0) {//нарушен порядок скобок
                        throw new IllegalArgumentException("Parentheses out of order");
                    }
                    arg.point = fractionalPart;
                    stackBigNumber.push(arg);
                    //stackDouble.push(insertAComma(arg, fractionalPart));
                    while (stackChar.peek() != '(') {
                        MyBigNumber val1 = stackBigNumber.pop();
                        MyBigNumber val2 = stackBigNumber.pop();
                        stackBigNumber.push(evaluateExpression(stackChar.pop(), val2, val1));
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
            arg.point = fractionalPart;
            stackBigNumber.push(arg);
            //stackDouble.push(insertAComma(arg, fractionalPart));
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

    /**
     * Для хранения чисел неограниченной длинны
     */
    private class MyBigNumber implements Comparable<MyBigNumber> {
        int scale; //для хранения знака

        char[] unscaledValue; //для хранения чисел

        int point = -1;

        public MyBigNumber(char[] str) {
            if (str.length == 0) {
                throw new RuntimeException();// todo строка пуста
            }
            if (str.length == 1 && str[0] == '0') { //число 0
                scale = 0;
            }
            if (str.length > 1) { //определяем знак числа
                scale = (str[0] == '-') ? -1 : 1;
            } else {
                scale = 1;
            }

            for (int i = str.length - 1, n = 0; i >= 0; i--, n++) {// todo находим точку
                if (str[i] == '.' || str[i] == ',') {
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
            int maxLenght = unscaledValue.length;
            if(point != -1) {
                if(maxLenght - point - 1 >= 0) {
                    maxLenght = maxLenght + 1;
                } else if(maxLenght - point - 1 < 0){
                    maxLenght = maxLenght + (point + 2 - maxLenght);
                }
            }
            maxLenght = scale == -1 ? maxLenght + 1 : maxLenght;

            char[] result = new char[maxLenght];
            if (scale == -1) {
                result[0] = '-';
            }
            if (point != -1) {
                result[maxLenght - point - 1] = '.';
            }

            for (int i = maxLenght - 1 , j = unscaledValue.length-1; i >= 0 ; i--) {
                if(result[i] == '-') {
                    break;
                }
                if(result[i] == '.') {
                    i--;
                }
                if(j>=0){
                    result[i] = unscaledValue[j--];
                } else {
                    result[i] = '0';
                }
            }

            if(point != -1){
                result = reverse(clearZero(reverse(result)));
                if(result[result.length-1] == '.'){
                    char[] resultWithoutPoint = new char[result.length-1];
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
            char[] resultArr = new char[arg1.length > arg2.length ? arg1.length : arg2.length];

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
        private int castingToAnInteger(MyBigNumber val1, MyBigNumber  val2) {
            int pointMax = val1.point > val2.point ? val1.point : val2.point;
            if (pointMax != -1) {
                for (int i = 0; i < pointMax; i++) {
                    val1.unscaledValue = new MyBigNumber(val1.multiply(new MyBigNumber("10")).toString()).unscaledValue;
                    if(val1.point > -1) val1.point--;
                }
                val1.unscaledValue = clearZero(val1.unscaledValue);
                val1.point = -1;
                for (int i = 0; i < pointMax; i++) {
                    val2.unscaledValue = new MyBigNumber(val2.multiply(new MyBigNumber("10")).toString()).unscaledValue;
                    if(val2.point > -1) val2.point--;
                }
                val2.unscaledValue = clearZero(val2.unscaledValue);
                val2.point = -1;
            }
            return pointMax;
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
            int pointMax = castingToAnInteger(this,myBigNumber);

            if (myBigNumber.scale == scale) {
                return new MyBigNumber(
                        this.scale,
                        sum(myBigNumber.getUnscaledValue(), unscaledValue),
                        pointMax - 1
                );
            } else {
                int cmp = compareMagnitude(myBigNumber);
                if (cmp == 0)
                    return new MyBigNumber("0");
                char[] resultMag = (cmp > 0 ? subtract(unscaledValue, myBigNumber.getUnscaledValue())
                        : subtract(myBigNumber.getUnscaledValue(), unscaledValue));
                return new MyBigNumber(cmp < 0 ? myBigNumber.getScale() : scale, resultMag, pointMax - 1);
            }
        }

        /**
         * Вычитание
         */
        public MyBigNumber subtract(MyBigNumber myBigNumber) {
            if (myBigNumber.scale == 0)
                return this;
            if (scale == 0)
                return myBigNumber;

            //точка
            int pointMax = castingToAnInteger(this,myBigNumber);

            if (myBigNumber.scale != scale) {
                return new MyBigNumber(
                        this.scale,
                        sum(unscaledValue, myBigNumber.getUnscaledValue()),
                        pointMax - 1
                );
            }

            int cmp = compareMagnitude(myBigNumber);
            if (cmp == 0) {
                return new MyBigNumber(0, new char[]{'0'});
            }
            char[] resultMag = (cmp > 0 ? subtract(unscaledValue, myBigNumber.getUnscaledValue())
                    : subtract(myBigNumber.getUnscaledValue(), unscaledValue));
            return new MyBigNumber(cmp > 0 ? 1 : -1, resultMag, pointMax - 1);
        }


        /**
         * Умножение
         */
        //this * val
        public MyBigNumber multiply(MyBigNumber val) {

            int point = -1;
            if(this.point != -1 && val.point == -1){
                point = this.point;
            }
            if(this.point == -1 && val.point != -1){
                point = val.point;
            }
            if(this.point != -1 && val.point != -1){
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
                return new MyBigNumber(new char[]{'1'});
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
                return new MyBigNumber(0, new char[]{'0'});
            }

            int resultScale = this.scale == val.scale ? 1 : -1;
            this.scale = 1;
            val.scale = 1;

            castingToAnInteger(this,val);

            char[] num1 = this.unscaledValue;
            char[] num2 = val.unscaledValue;
            // Выделите пробел для хранения результата операции.
            // Длинное число num1 * Длинное число num2, результат не будет превышать длинную num1 + num2

            MyBigNumber result = new MyBigNumber(0, new char[]{'0'});
            int indexPoint = -1;

            int indexNum1 = 0;
            MyBigNumber dividend = new MyBigNumber(0, new char[]{'0'});

            while ((indexNum1 < num1.length || compareMagnitude(dividend.getUnscaledValue(), new char[]{'0'}) != 0) && indexPoint < 9) {
                //бесплатно
                if (indexNum1 >= num1.length) {
                    dividend = new MyBigNumber(multiply(dividend.getUnscaledValue(), new char[]{'1', '0'}));
                    indexPoint++;
                } else {
                    if (dividend.compareTo(new MyBigNumber(0, new char[]{'0'})) == 0) {
                        dividend = new MyBigNumber(new char[]{num1[indexNum1++]});
                    } else {
                        dividend = new MyBigNumber(
                                sum(multiply(dividend.getUnscaledValue(), new char[]{'1', '0'}), new char[]{num1[indexNum1++]}));
                    }
                }

                //платно
                while (compareMagnitude(dividend.getUnscaledValue(), num2) < 0) {
                    if (indexNum1 >= num1.length) {
                        dividend = new MyBigNumber(multiply(dividend.getUnscaledValue(), new char[]{'1', '0'}));
                        indexPoint++;
                    } else {
                        dividend = new MyBigNumber(
                                sum(multiply(dividend.getUnscaledValue(), new char[]{'1', '0'}), new char[]{num1[indexNum1++]}));
                    }
                }

                MyBigNumber mnoj = getMnoj(dividend, val);

                dividend = dividend.subtract(val.multiply(mnoj));

                if (result.equals(new MyBigNumber(0, new char[]{'0'}))) {
                    result.add(mnoj);
                } else {
                    result = new MyBigNumber(
                            sum(multiply(result.getUnscaledValue(), new char[]{'1', '0'}), mnoj.unscaledValue));
                }
            }

            if(indexPoint != -1) {
                result.point = indexPoint + 1;
            }

            result.scale = resultScale;
            return result;
        }

        /**
         * Рекурсивный поиск множителя
         */
        private MyBigNumber getMnoj(MyBigNumber dividend, MyBigNumber divide) {
            MyBigNumber mnoj = new MyBigNumber(1, new char[]{'1'});

            while (compareMagnitude(divide.pow(mnoj.pow(new MyBigNumber(new char[]{'2'}))).getUnscaledValue(), dividend.unscaledValue) < 0) {
                mnoj = mnoj.pow(new MyBigNumber(new char[]{'2'}));
            }

            dividend = dividend.subtract(divide.multiply(mnoj));

            if (dividend.compareTo(divide) < 0) {
                return mnoj;
            }

            return mnoj.add(getMnoj(dividend, divide));
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

        private int getNumber(int val) {
            return (val - '0');
        }

        private char getChar(int val) {
            return (char) (val + '0');
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
            return compareMagnitude(unscaledValue, val.getUnscaledValue());
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
    }

}
