package cn.enaium.learn;

import cn.enaium.learn.classes.Test1;
import cn.enaium.learn.classes.Test2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Enaium
 */
public class Main {

    private static final ClassContainer classContainer = new ClassContainer();

    public static void main(String[] args) {
        classContainer.create(Test1.class).render();
    }
}
