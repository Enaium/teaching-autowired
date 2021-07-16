package cn.enaium.learn;

import cn.enaium.learn.classes.Test1;
import cn.enaium.learn.classes.Test2;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Enaium
 */
public class ClassContainer {

    private final HashMap<Class<?>, Object> classes = new HashMap<>();

    public ClassContainer() {
        List<Class<?>> scanClasses = new ArrayList<>(List.of(Test1.class, Test2.class));
        scanClasses.forEach(it -> {
            try {
                classes.put(it, it.getConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        autowired();
    }

    private void autowired() {
        for (Map.Entry<Class<?>, Object> classObjectEntry : classes.entrySet()) {
            for (Field declaredField : classObjectEntry.getKey().getDeclaredFields()) {
                declaredField.setAccessible(true);
                if (classes.get(declaredField.getType()) != null) {
                    try {
                        declaredField.set(classObjectEntry.getValue(), classes.get(declaredField.getType()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public <T> T create(Class<T> klass, Object instance) {
        classes.put(klass, instance);
        autowired();
        return (T) classes.get(klass);
    }

    public <T> T create(Class<T> klass) {
        if (classes.get(klass) == null) {
            try {
                classes.put(klass, klass.getConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return (T) classes.get(klass);
    }

    public HashMap<Class<?>, Object> getClasses() {
        return classes;
    }
}
