package com.cyvan.designpattern;

import java.util.ArrayList;

/**
 * 观察者模式
 * 案例采用: (温度获取器 - 中间Subject) - 观察者布告板
 */
public class ObserverPattern {

    public static void main(String[] args) {
        TempGetterSubject subject = new TempGetterSubject();
        TempObserver observer1 = new TempObserver(subject);
        TempObserver observer2 = new TempObserver(subject);
        TempObserver observer3 = new TempObserver(subject);
        System.out.println("第一次温度更新: ");
        subject.setUpdate();
        System.out.println("第二次温度更新: ");
        subject.setUpdate();
        System.out.println("第三次温度更新: ");
        subject.setUpdate();
    }

}

interface Subject {
    /**
     * 注册观察者
     * @param o
     */
    void registerObserver(Observer o);

    /**
     * 移除观察者
     * @param o
     */
    void removeObserver(Observer o);

    /**
     * 通知所有观察者
     */
    void notifyAllObservers();
}

class TempGetterSubject implements Subject {

    private int T; // T属性
    private int H; // H属性
    private int P; // P属性

    private ArrayList<Observer> observers; // 当前注册的观察者列表

    public TempGetterSubject() {
        this.observers = new ArrayList<>(); // 初始化列表
    }
    /**
     * 手动更新温度信息代替其感应实际温度信息
     */
    public void setUpdate() {
        // 自定义温度增长规则
        this.T = this.T == 0 ? 5 : this.T * 2 + 1;
        this.H = this.H == 0 ? 5 : this.H * 2 + 1;
        this.P = this.P == 0 ? 5 : this.P * 2 + 1;
        // 温度更新后即进行通知
        this.notifyAllObservers();
    }

    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int index = this.observers.indexOf(o);
        this.observers.remove(index);
    }

    @Override
    public void notifyAllObservers() {
        for(Observer o : observers) {
            o.update(this.T, this.H, this.P);
        }
    }


}

interface Observer {
    /**
     * 观察者更新信息
     * @param t 属性1
     * @param h 属性2
     * @param p 属性3
     */
    void update(int t, int h, int p);
}

interface DisplayInfo {
    /**
     * 展示信息的功能
     */
    void display();
}



class TempObserver implements Observer, DisplayInfo {

    // 三个属性
    private int T;
    private int H;
    private int P;

    private Subject subject; // 中间通知者

    public TempObserver(Subject subject) {
        this.subject = subject; // 设置此观察者的通知者
        this.subject.registerObserver(this); // 通知者注册观察者
    }

    @Override
    public void update(int t, int h, int p) {
        // 更新信息
        this.T = t;
        this.H = h;
        this.P = p;
        // 展示新信息
        this.display();
    }

    @Override
    public void display() {
        System.out.println("当前布告板为" + this + ", 温度属性为: t = " + this.T + ", H = " + this.H + ", P = " + this.P + ".");
    }
}
