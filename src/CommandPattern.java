package com.cyvan.designpattern;

/**
 * 命令模式
 * 案例采用: 遥控器 - 多个功能(灯开关)
 */
public class CommandPattern {

    public static void main(String[] args) {
        Light light = new Light("Light_circle");
        Command onCommand = new CommandLightOn(light);
        Command offCommand = new CommandLightOff(light);
        CommandPanel panel = new CommandPanel(onCommand, offCommand);
        panel.onBtnPressed();
        panel.offBtnPressed();
    }

}

class CommandPanel {

    private Command onCommand;
    private Command offCommand;

    public CommandPanel(Command onCommand, Command offCommand) {
        this.onCommand = onCommand;
        this.offCommand = offCommand;
    }

    public void onBtnPressed() {
        onCommand.execute();
    }

    public void offBtnPressed() {
        offCommand.execute();
    }

    public void setOnCommand(Command onCommand) {
        this.onCommand = onCommand;
    }

    public void setOffCommand(Command offCommand) {
        this.offCommand = offCommand;
    }
}

class Light {

    private String name;

    public Light(String name) {
        this.name = name;
    }

    public void lightOn() {
        System.out.println("Light " + this.name + " turn on.");
    }

    public void lightOff() {
        System.out.println("Light " + this.name + " turn off.");
    }

}

interface Command {

    void execute();

}

class CommandLightOn implements Command {

    Light light;

    public CommandLightOn(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.lightOn();
    }
}

class CommandLightOff implements Command {

    Light light;

    public CommandLightOff(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.lightOff();
    }

}