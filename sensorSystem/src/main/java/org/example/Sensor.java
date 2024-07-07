package org.example;
import com.microsoft.azure.sdk.iot.device.*;
import com.microsoft.azure.sdk.iot.device.exceptions.IotHubClientException;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;

public class Sensor extends Thread{

private String connString;
private String name;
private int lowerBound;
private int upperBound;
private int delayTime;
private DeviceClient client;

DecimalFormat messageFormat;
DecimalFormatSymbols formatSymbols;


public Sensor(String sensorName, int measurementDelay, int lowerMeasurementBound, int upperMeasurementBound){

    lowerBound = lowerMeasurementBound;
    upperBound = upperMeasurementBound;
    name = sensorName;
    delayTime = measurementDelay;

    formatSymbols = new DecimalFormatSymbols(Locale.US);
    formatSymbols.setDecimalSeparator('.');

    messageFormat = new DecimalFormat("#.######", formatSymbols);
}

public void connectToCloud(String connectionString) {

    client = new DeviceClient(connectionString, IotHubClientProtocol.MQTT);

}

public void sendMessage(){

    String formattedMeasure = messageFormat.format(measure());

    String msgStr = String.format("{\"data\": \"%s\"}", formattedMeasure);
    Message msg = new Message(msgStr);
    System.out.println(msgStr);
    msg.setContentEncoding("UTF-8");
    msg.setContentType("application/json");


    client.sendEventAsync(msg, (responseStatus, callbackContext, exception) -> {
        if (exception == null) {
            System.out.println("Message sent.");
        } else {
            System.err.println("Failed to send message: " + exception);
        }
    }, null);
}

public float measure(){
    Random random = new Random();
    return random.nextFloat() * (upperBound-lowerBound) + lowerBound;
}

public void run() {
    try {
        client.open(false);
    } catch (IotHubClientException e) {
        throw new RuntimeException(e);
    }
    for (int i = 0; i < 20; i++) {
        sendMessage();
        try {
            Thread.sleep(delayTime*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    client.close();
}
}
