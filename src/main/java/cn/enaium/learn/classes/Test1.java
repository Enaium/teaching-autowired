package cn.enaium.learn.classes;

import cn.enaium.learn.annotation.Autowired;

/**
 * @author Enaium
 */
public class Test1 {
    @Autowired
    private Test2 test2;

    @Autowired
    private Test3 test3;

    public void render() {
        test2.render();
        test3.render();
    }
}
