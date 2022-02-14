import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Reflection + ООП + дизайн паттерны - Этап 1
 * @author Александр Братчин
 * @version 1.0
 */
public class MyReflection {

    Class<?> aClass;

    public MyReflection(Class<?> aClass) {
        this.aClass = aClass;
    }

    /**
     *  Метод получения списока методов
     * @return возвращает список методов
     */
    public List<Method> getMethods() {
        return Arrays.stream(aClass.getDeclaredMethods()).collect(Collectors.toList());
    }

    /**
     *  Метод получения списока методов, которые проаннотированы аннотацией, заданной в параметры
     * @return возвращает список методов
     */
    public List<Method> getMethodsByAnnotation(Class<? extends Annotation> annotationClass) {
        return getMethods().stream().filter(method -> method.isAnnotationPresent(annotationClass)).toList();
    }

    /**
     *  Метод, создающий экземпляр класса Class через default constructor, если default constructor отсутствует или создать экземпляр не удалось выкидывает run-time исключение.
     * @return возвращает экземпляр класса Class
     */
    public static Class<?> createAnInstanceOfTheClass(Class<?> cl) {
        try {
            if (cl.getConstructors().length == 0 && cl.getDeclaredConstructors().length == 1) {
                return cl.getDeclaredConstructor().newInstance().getClass();
            } else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     *  Метод, который вызывает рекурсивный метод возвращающий список аннотаций
     * @return возвращает список аннотаций
     */
    public List<Annotation> getAnnotations() {
        return getFullAnnotation(aClass);
    }

    /**
     *  Рекурсивный метод, который пробегает по всем предкам и возвращает список аннотаций класса, если аннотации дублируются, то используется та, которая наследника.
     * @return возвращает список аннотаций
     */
    private List<Annotation> getFullAnnotation(Class<?> aClass){

        List<Annotation> annotations = new ArrayList<>();

        if (aClass.getSuperclass()!=null){
            annotations.addAll(getFullAnnotation(aClass.getSuperclass()));
        }

        List<Annotation> annotations2 = Arrays.stream(aClass.getAnnotations()).collect(Collectors.toList());

        for (Annotation a:
                annotations) {
            String name = a.annotationType().getName();
            if(!annotations2.stream()
                    .map(annotation -> annotation.annotationType().getName()).toList().contains(name)){
                annotations2.add(a);
            }
        }

        return annotations2;
    }

}
