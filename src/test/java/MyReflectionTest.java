import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.assertj.core.api.Assertions.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ParameterIsPresent {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface A1 {
    int value() default 1;
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface A2 {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface A3 {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface A4 {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface AI {
    int value() default 1;
}

@AI(0)
interface MyTestInterface {

}

@A1(2)
@A4
class MyTestParent {
    public void publicMethod(){}
}

@A1(3)
@A2
@A3
@AI(10)
class MyTestChild extends MyTestParent implements MyTestInterface {

    public void publicMethod() {}

    @ParameterIsPresent()
    public void publicMethod(int a){}

    @ParameterIsPresent()
    public void publicMethodWithParams(int a){}

    private void privateMethod(){}

    @ParameterIsPresent()
    private void privateMethodWithParams(int a){}

    protected void protectedMethod(){}

    @ParameterIsPresent()
    protected void protectedMethodWithParams(int a){}

}

class MyTestConstructorWithParam {

    int a;

    public MyTestConstructorWithParam(int a) {
        this.a = a;
    }

}

class MyTestConstructorDefault {

}

class MyTestConstructorNoArg {
    public MyTestConstructorNoArg() {
    }
}

class MyReflectionTest {

    MyTestChild testChild = new MyTestChild();

    @Test()
    void getMethods() {

        MyReflection myReflectionTest = new MyReflection(testChild.getClass());

        myReflectionTest.getMethods().forEach(System.out::println);

        assertThat(myReflectionTest.getMethods().size()).isEqualTo(7);

    }

    @Test
    void getMethodsByAnnotation() {

        MyReflection myReflectionTest = new MyReflection(testChild.getClass());

        myReflectionTest.getMethodsByAnnotation(ParameterIsPresent.class).forEach(System.out::println);

    }

    @Test
    void createAnInstanceOfTheClass() {

        Class<?> aClass = MyReflection.createAnInstanceOfTheClass(MyTestConstructorDefault.class);

        assertThat(aClass.getName()).isEqualTo("MyTestConstructorDefault");

        try {
            MyReflection.createAnInstanceOfTheClass(MyTestConstructorNoArg.class);
        }catch (RuntimeException runtimeException){
            System.out.println("TestConstructorNoArg");
        }

        try {
            MyReflection.createAnInstanceOfTheClass(MyTestConstructorWithParam.class);
        }catch (RuntimeException runtimeException){
            System.out.println("TestConstructorWithParam");
        }

    }

    @Test()
    void getAnnotations() {

        MyReflection myReflectionTest = new MyReflection(testChild.getClass());

        assertThat(myReflectionTest.getAnnotations().size()).isEqualTo(5);

    }


}