package ru.calculate;


public final class Calculator {

    private char[] expression;
    private int indexOperator;

    public Calculator(String expression) {
        this.expression = expression.toCharArray();
        int minusIndex = indexOf('-');
        int plusIndex = indexOf('+');
        indexOperator = (minusIndex!=-1 && plusIndex!=-1)? minusIndex<plusIndex?minusIndex:plusIndex: minusIndex>plusIndex?minusIndex:plusIndex;
        if(indexOperator == -1) throw new IllegalArgumentException("Неверный знак операции!");
    }

    public int calculate(){
        return getOperator() == '+'? getArg1() + getArg2():getArg1() - getArg2();
    }

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

    private int degreeOfTen(int degree){
        int result = 1;
        for (int i = 0; i < degree; i++) {
            result*=10;
        }
        return result;
    }

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

    private int getNumber (int index) {
        int number = (expression[index] - '0');
        if(number>=10 || number<0) throw new IllegalArgumentException("Не цифра!");
        return number;
    }

    private char getOperator() {
        return indexOperator>0?expression[indexOperator]:' ';
    }

    private int indexOf(char c){
        for (int i = 1; i < expression.length; i++) {
            if(expression[i] == c) return i;
        }
        return -1;
    }

}
