package com.cyvan.designpattern;

/**
 * 装饰者模式
 * 案例采用: 原始饮料 - 各类配料装饰
 */
public class DecoratorPattern {

    public static void main(String[] args) {
        /**
         * drink1 - 1
         * drink2 - 2
         * mocha  - 10
         * soy    - 20
         */
        Base drink1 = new Drink1();
        Mocha mochaDrink = new Mocha(drink1); // 抹茶
        Base drink2 = new Drink2();
        Mocha tmpDrink = new Mocha(drink2);
        Soy soyDrink = new Soy(tmpDrink); // 豆浆抹茶
        System.out.println(mochaDrink.getDesc() + " --- " + mochaDrink.cost());
        System.out.println(soyDrink.getDesc() + " --- " + soyDrink.cost());
    }

}

abstract class Base {
    String desc = "base"; // 描述

    public String getDesc() {
        return this.desc;
    }

    public abstract int cost(); // 花费
}

abstract class CondimentBase extends Base {
    public abstract String getDesc(); // 连接各个desc
}

class Drink1 extends Base {

    public Drink1() {
        desc = "Drink1";
    }

    @Override
    public int cost() {
        return 1;
    }
}

class Drink2 extends Base {

    public Drink2() {
        desc = "Drink2";
    }

    @Override
    public int cost() {
        return 2;
    }
}

class Mocha extends CondimentBase {

    Base base; // 待修饰

    public Mocha(Base base) {
        this.base = base;
    }

    @Override
    public int cost() {
        return 10 + base.cost();
    }

    @Override
    public String getDesc() {
        return "Mocha " + this.base.getDesc();
    }
}

class Soy extends CondimentBase {

    Base base; // 待修饰

    public Soy(Base base) {
        this.base = base;
    }

    @Override
    public int cost() {
        return 20 + this.base.cost();
    }

    @Override
    public String getDesc() {
        return "Soy " + this.base.getDesc();
    }
}