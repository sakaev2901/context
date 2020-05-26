import h.A;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        Class c = A.class;
        Annotation[] annotations = c.getDeclaredAnnotations();
        Annotation anno = annotations[0];
        System.out.println(anno.annotationType().getName().equals("h.MyAnno"));
        Class<? extends Annotation> annoClass = anno.annotationType();
        try {
            System.out.println(annoClass.getDeclaredMethod("value").invoke(anno, (Object[]) null));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
    }
}
