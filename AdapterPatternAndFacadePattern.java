package com.cyvan.designpattern;

/**
 * 适配器模式 && 外观模式
 * 案例采用: dog - cat 适配器模式
 *          tv . light     外观模式
 */
public class AdapterPatternAndFacadePattern {

    public static void main(String[] args) {
        System.out.println("Adapter.");
        Cat cat = new BrownCat();
        Dog dog = new BrownDog();
        dog.bark();
        dog.run();
        Dog fakeDog = new Cat2DogAdapater(cat);
        fakeDog.bark();
        fakeDog.run();
        System.out.println("\nFacade.");
        Tv tv = new Tv();
        Curtain curtain = new Curtain();
        RoomFacade facade = new RoomFacade(tv, curtain);
        facade.enterRoom();
        facade.exitRoom();
    }

}

interface Dog {

    void bark();
    void run();

}

interface Cat {

    void miao();
    void run();

}

class BrownDog implements Dog {

    @Override
    public void bark() {
        System.out.println(this.getClass() + " bark.");
    }

    @Override
    public void run() {
        System.out.println(this.getClass() + " run.");
    }
}

class BrownCat implements Cat {

    @Override
    public void miao() {
        System.out.println(this.getClass() + " miao.");
    }

    @Override
    public void run() {
        System.out.println(this.getClass() + " run.");
    }
}

class Cat2DogAdapater implements Dog {

    private Cat cat;

    public Cat2DogAdapater(Cat cat) {
        this.cat = cat;
        System.out.println("Start cat to dog adapter...");
    }

    @Override
    public void bark() {
        for(int i = 0; i < 2; ++i) {
            System.out.println("Dog bark loudly ... Cat miao for " + (i + 1) + " times");
            this.cat.miao();
        }
    }

    @Override
    public void run() {
        this.cat.run();
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }
}

class Tv {

    public void On() {
        System.out.println("Turn on the tv.");
    }

    public void Off() {
        System.out.println("Turn off the tv.");
    }

}

class Curtain {

    public void On() {
        System.out.println("Turn on the curtain.");
    }

    public void Off() {
        System.out.println("Turn off the curtain.");
    }

}

class RoomFacade {

    private Tv tv;
    private Curtain curtain;

    public RoomFacade(Tv tv, Curtain curtain) {
        this.tv = tv;
        this.curtain = curtain;
    }

    public void enterRoom() {
        System.out.println("When enter the room, tv and curtain will auto on:");
        this.tv.On();
        this.curtain.On();
    }

    public void exitRoom() {
        System.out.println("When exit the room, tv and curtain will auto off:");
        this.tv.Off();
        this.curtain.Off();
    }

    public void setTv(Tv tv) {
        this.tv = tv;
    }

    public void setCurtain(Curtain curtain) {
        this.curtain = curtain;
    }
}
