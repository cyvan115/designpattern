package com.cyvan.designpattern;

/**
 * 工厂方法模式
 * 案例采用: pizza - some place. some style
 * pizza - store 工厂方法模式
 * pizza - ingredient 抽象工厂模式
 */
public class FactoryPattern {

    public static void main(String[] args) {
        PizzaStore store1 = new PizzaStoreA();
        PizzaStore store2 = new PizzaStoreB();
        Pizza pizza1 = store1.order("A");
        Pizza pizza2 = store2.order("S");
        pizza1.prepare();
        pizza1.make();
        System.out.println("============");
        pizza2.prepare();
        pizza2.make();
    }

}

abstract class PizzaStore {

    abstract Pizza order(String type);

}

abstract class Pizza {

    abstract void prepare();

    void make() {
        System.out.println("Making...");
    }

}

interface IngredientFactory {

    void getA();
    void getB();
    void getAll();

}

class IngredientFactoryA implements IngredientFactory {

    @Override
    public void getA() {
        System.out.println("Get source A - type A.a");
    }

    @Override
    public void getB() {
        System.out.println("Get source B - type A.b");
    }

    @Override
    public void getAll() {
        this.getA();
        this.getB();
    }
}

class IngredientFactoryB implements IngredientFactory {

    @Override
    public void getA() {
        System.out.println("Get source A - type B.a");
    }

    @Override
    public void getB() {
        System.out.println("Get source B - type B.b");
    }

    @Override
    public void getAll() {
        this.getA();
        this.getB();
    }
}

class PizzaA extends Pizza {

    IngredientFactory ingredientFactory;

    public PizzaA(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    void prepare() {
        System.out.println("This is PizzaA");
        this.ingredientFactory.getAll();
    }
}

class PizzaB extends Pizza {

    IngredientFactory ingredientFactory;

    public PizzaB(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    void prepare() {
        System.out.println("This is PizzaB");
        this.ingredientFactory.getAll();
    }
}

class PizzaStoreA extends PizzaStore {

    @Override
    Pizza order(String type) { // 可传入pizza type
        IngredientFactory ingredientFactory = new IngredientFactoryA(); // 抽象工厂
        Pizza pizza = null;
        if("A".equals(type)) {
            pizza = new PizzaA(ingredientFactory);
        } else {
            pizza = new PizzaB(ingredientFactory);
        }
        return pizza;
    }
}

class PizzaStoreB extends PizzaStore {

    @Override
    Pizza order(String type) {
        IngredientFactory ingredientFactory = new IngredientFactoryB();
        Pizza pizza = null;
        if("A".equals(type)) {
            pizza = new PizzaA(ingredientFactory);
        } else {
            pizza = new PizzaB(ingredientFactory);
        }
        return pizza;
    }

}