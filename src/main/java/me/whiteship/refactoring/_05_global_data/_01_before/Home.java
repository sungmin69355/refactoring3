package me.whiteship.refactoring._05_global_data._01_before;

public class Home {

    public static void main(String[] args) {
        System.out.println(Thermostats.getTargetTemperature());
        Thermostats.setTargetTemperature(100);
        Thermostats.setFahrenheit(false);
    }
}
