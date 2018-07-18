package com.cyvan.designpattern;

/**
 * 策略模式
 * 案例采用: duck - fly. quack methods
 */
public class StrategyPattern {

    public static void main(String[] args) {
        Duck duck = new TempDuck();
        duck.setFly(new CanFly());
        duck.setQuack(new CanQuack());
        duck.displayName();
        duck.show();
        duck.setFly(new CanNotFly());
        System.out.println("============");
        duck.displayName();
        duck.show();
    }

}

abstract class Duck {

    Fly fly; // 功能1 - 委托实现
    Quack quack; // 功能2 - 委托实现

    public void setFly(Fly fly) { // 修改实现
        this.fly = fly;
    }

    public void setQuack(Quack quack) { // 修改实现
        this.quack = quack;
    }

    public void show() {
        this.fly.fly();
        this.quack.quack();
    }

    public abstract void displayName();

}

class TempDuck extends Duck {

    @Override
    public void displayName() {
        System.out.println("I am TempDuck.");
    }

}

interface Fly {

    void fly();

}

interface Quack {

    void quack();

}

class CanFly implements Fly {

    @Override
    public void fly() {
        System.out.println("I can fly");
    }
}

class CanNotFly implements Fly {

    @Override
    public void fly() {
        System.out.println("I can not fly");
    }

}

class CanQuack implements Quack {

    @Override
    public void quack() {
        System.out.println("I can quack");
    }

}

