package sensorapp.ui;


import common.SensorService;
import common.model.Sensor;
import common.model.SensorDTO;

import java.util.Random;
import java.util.Scanner;

public class UI {
    private final SensorService sensorService;

    public UI(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    private Sensor readSensor() {
        Scanner stdin = new Scanner(System.in);
        System.out.println("Name:");
        String name = stdin.next();
        System.out.println("Id: ");
        int id = stdin.nextInt();
        System.out.println("Lower bound:");
        int lowerBound = stdin.nextInt();
        System.out.println("Upper bound: ");
        int upperBound = stdin.nextInt();
        return new Sensor(id, name, lowerBound, upperBound);
    }

    private void generateMeasurement(Sensor sensor) {
        Random random = new Random();
        while (true) {
            int number = random.ints(sensor.getLowerBound(), sensor.getUpperBound()).findFirst().getAsInt();
            try {
                Thread.sleep(new Random().ints(0, 2000).findFirst().getAsInt());
            } catch (InterruptedException exception) {
                System.out.println("Error on thread sleep");
            }
            sensorService.sendSensor(new SensorDTO(sensor.getId(), sensor.getName(), number));
        }
    }
    public void run() {
        generateMeasurement(readSensor());
    }
}
