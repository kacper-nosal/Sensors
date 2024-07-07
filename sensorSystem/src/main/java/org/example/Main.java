package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int tempLower = -10;
        int tempUpper = 30;
        int tempDelay = 5;

        Sensor temperatureSensor = new Sensor("TemperatureSensor",tempDelay,tempLower,tempUpper);
        temperatureSensor.connectToCloud("HostName=FlowerPower.azure-devices.net;DeviceId=TemperatureSensor;SharedAccessKey=/tmTU4T7Gt/i9fomcoioZROjT3PtfMJTqAIoTBi4Fzs=");

        int velLower = 0;
        int velUpper = 150;
        int velDelay = 5;
        Sensor velocitySensor = new Sensor("VelocitySensor",velDelay,velLower,velUpper);
        velocitySensor.connectToCloud("HostName=FlowerPower.azure-devices.net;DeviceId=VelocitySensor;SharedAccessKey=1pbH9xAo/Q+CE0zH9BqA91ctWe/zzSbELAIoTOfePro=");

        int humLower = 0;
        int humUpper = 100;
        int humDelay = 10;
        Sensor humiditySensor = new Sensor("HumiditySensor",humDelay,humLower,humUpper);
        humiditySensor.connectToCloud("HostName=FlowerPower.azure-devices.net;DeviceId=HumiditySensor;SharedAccessKey=DSbBcLxPsY4NBMEtUxo5EEpWH1bdp2wJqAIoTIN2Bi0=");

        int fuelLower = 0;
        int fuelUpper = 100;
        int fuelDelay = 30;
        Sensor fuelLevelSensor = new Sensor("FuelLevelSensor",fuelDelay,fuelLower,fuelUpper);
        fuelLevelSensor.connectToCloud("HostName=FlowerPower.azure-devices.net;DeviceId=FuelLevelSensor;SharedAccessKey=IbG7yTXT8CBTmlDtuYsnpojUr5nmwbrv4AIoTMS2jR0=");

        velocitySensor.start();
        temperatureSensor.start();
        humiditySensor.start();
        fuelLevelSensor.start();

    }
}