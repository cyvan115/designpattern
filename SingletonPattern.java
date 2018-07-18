package com.cyvan.designpattern;

/**
 * 单例模式
 * 案例采用: 4种实现方式
 */
public class SingletonPattern {

    public static void main(String[] args) {
        Singleton1 singleton1 = Singleton1.getInstance();
        Singleton2 singleton2 = Singleton2.getInstance();
        Singleton3 singleton3 = Singleton3.getInstance();
        Singleton4 singleton4 = Singleton4.getInstance();
    }

}

class Singleton1 {

    private static Singleton1 singleton;

    private Singleton1() { }

    public static Singleton1 getInstance() {
        if(singleton == null) { // 多线程问题
            singleton = new Singleton1();
        }
        return singleton;
    }
}

class Singleton2 {

    private static Singleton2 singleton;

    private Singleton2() { }

    public static synchronized Singleton2 getInstance() { // 解决多线程同步问题, 但是性能降低
        if(singleton == null) { // 多线程问题
            singleton = new Singleton2();
        }
        return singleton;
    }
}

class Singleton3 {

    private static Singleton3 singleton = new Singleton3(); // 类加载时即初始化

    private Singleton3() {}

    public static Singleton3 getInstance() {
        return singleton;
    }

}

class Singleton4 {

    private volatile static Singleton4 singleton;

    private Singleton4() {}

    public static Singleton4 getInstance() { // 双重检查 + 锁
        if(singleton == null) {
            synchronized (Singleton4.class) {
                if(singleton == null) {
                    singleton = new Singleton4();
                }
            }
        }
        return singleton;
    }

}
