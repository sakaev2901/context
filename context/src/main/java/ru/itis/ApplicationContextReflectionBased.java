package ru.itis;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class ApplicationContextReflectionBased implements ApplicationContext {

    private Reflections reflections = new Reflections();

    @Override
    public void scan(Object demo) {
        Class tClass = demo.getClass();
        Field[] fields = tClass.getDeclaredFields();
        for (Field field:
                fields) {
            Class fieldClass = field.getType();
            Class<?> fieldImpl = null;
            if (!isClass(fieldClass)) {
                Set<Class> classes = reflections.getSubTypesOf(fieldClass);
                for (Class implClass:
                     classes) {
                    fieldImpl = implClass;
                }
            } else  {
                  fieldImpl = fieldClass;
            }
            try {
                fieldImpl.getMethod("getComponentName");
                field.setAccessible(true);
                try {
                    Object o = fieldImpl.newInstance();
                    field.set(demo, o);
                    scan(o);
                    System.out.println(field.getName() + " SUCCESS");
                } catch (IllegalAccessException | InstantiationException e) {
                    throw new IllegalStateException(e);
                }
            } catch (NoSuchMethodException e) {
                //ignore
                System.err.println(field.getName() + " is not ru.itis.Component");
            }

        }
    }

   public  <T> T getComponent(Class<T> componentType, String name) {
       T model;
       if (isClass(componentType)) {
           try {
               model = componentType.newInstance();
//               Method method = componentType.getMethod("getComponentName");
//               String componentName = (String)method.invoke(model);
               if (getComponentName(componentType).equals(name)) {
                   scan(model);
                   System.out.println("SUCCESS");
                   return model;
               }

           } catch (Exception e) {
               throw new IllegalStateException(e);
           }
       } else {
           Set<Class<? extends T>> implementations =  reflections.getSubTypesOf(componentType);
           for (Class<? extends T> tClass : implementations) {
               try {
                   if (getComponentName(tClass).equals(name) || getMapping(tClass).equals(name)) {
                       T obj = tClass.newInstance();
                       scan(obj);
                       System.out.println("SUCCESS");
                       return obj;
                   }
               } catch (Exception e) {
                   throw new IllegalStateException(e);
               }
           }
       }
       return null;
//       throw new IllegalStateException("no such component");
    }

    private boolean isClass(Class tClass) {
        String[] parts = tClass.toString().split(" ");
        return parts[0].equals("class");
    }

    private <T> String getComponentName(Class<? extends T> tClass ) {
        try {
            Method method = tClass.getMethod("getComponentName");
            T obj = tClass.newInstance();
            return (String) method.invoke(obj);
        } catch (Exception e) {
            return "NO SUCH METHOD";
        }
    }

    private <T> String getMapping(Class<? extends T> tClass) {
        String mapping = null;
        Annotation[] annotations = tClass.getDeclaredAnnotations();
        for (Annotation annotation:
             annotations) {
            String[] nameWithPackage = annotation.annotationType().getName().split("\\.");
            if (nameWithPackage[nameWithPackage.length - 1].equals("Mapping")) {
                Class<? extends  Annotation> annotationType = annotation.annotationType();
                try {
                    mapping = (String) annotationType.getDeclaredMethod("value").invoke(annotation, (Object[])null);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
//        annotations[0].annotationType().getTypeName()
        return mapping;
    }
}
