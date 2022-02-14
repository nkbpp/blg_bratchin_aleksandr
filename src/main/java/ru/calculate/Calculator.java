package ru.calculate;

/**
 * Разработать класс, который бы вычислял значение выражения по формуле.
 * Само выражение представляет из себя строку вида "<число><знак сложения-вычитания><число>"
 * Примеры, "2+6" "2-6"
 * @author Александр Братчин
 * @version 1.0
 */

public final class Calculator {

    private char[] expression;
    private int indexOperator;

    public Calculator(String expression) {
        this.expression = expression.toCharArray();//единственное место где использовался сторонний метод класса String
        int minusIndex = indexOf('-');
        int plusIndex = indexOf('+');
        indexOperator = (minusIndex!=-1 && plusIndex!=-1)? minusIndex<plusIndex?minusIndex:plusIndex: minusIndex>plusIndex?minusIndex:plusIndex;
        if(indexOperator == -1) throw new IllegalArgumentException("Неверный знак операции!");
    }

    /**
     *  Метод получения результата выполнения выражения
     * @return возвращает int значение
     */
    public int calculate(){
        return getOperator() == '+'? getArg1() + getArg2():getArg1() - getArg2();
    }

    /**
     * Вспомогательный метод получения первого числа из строки
     * число может быть отрицательным
     * @return возвращает int значение первого числа в выражении
     */
    private int getArg1() {
        int arg1 = 0;
        for (int i = indexOperator-1,j=0; i >= 0; i--) {
            if(i==0 && expression[i] == '-'){
                arg1 *= -1;
            } else {
                arg1 += (getNumber(i) * degreeOfTen(j++));
            }
        }
        return arg1;
    }

    /**
     * Вспомогательный метод вычисления втепеней числа 10
     * @return возвращает int значение 10 в степени degree
     */
    private int degreeOfTen(int degree){
        int result = 1;
        for (int i = 0; i < degree; i++) {
            result*=10;
        }
        return result;
    }

    /**
     * Вспомогательный метод получения второго числа из строки
     * число может быть отрицательным
     * @return возвращает int значение второго числа в выражении
     */
    private int getArg2() {
        int arg2 = 0;
        for (int i = expression.length-1,j=0; i > indexOperator; i--) {
            if(i==indexOperator+1 && expression[i] == '-'){
                arg2 *= -1;
            } else {
                arg2 += (getNumber(i) * degreeOfTen(j++));
            }
        }
        return arg2;
    }

    /**
     * Вспомогательный метод перевода символа числа в число
     * если символ не чило выпадет исключение IllegalArgumentException
     * @return возвращает int число
     */
    private int getNumber (int index) {
        int number = (expression[index] - '0');
        if(number>=10 || number<0) throw new IllegalArgumentException("Не цифра!");
        return number;
    }

    /**
     * Вспомогательный метод получения символа операции
     * @return возвращает char символ операции
     */
    private char getOperator() {
        return indexOperator>0?expression[indexOperator]:' ';
    }

    /**
     * Вспомогательный метод поиска символа
     * @return возвращает индекс символа
     */
    private int indexOf(char c){
        for (int i = 1; i < expression.length; i++) {
            if(expression[i] == c) return i;
        }
        return -1;
    }

}
