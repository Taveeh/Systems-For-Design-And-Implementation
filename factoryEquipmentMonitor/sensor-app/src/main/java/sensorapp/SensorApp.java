package sensorapp;

import common.SensorService;
import sensorapp.service.SensorServiceClient;
import sensorapp.tcp.TcpClient;
import sensorapp.ui.UI;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SensorApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        TcpClient tcpClient = new TcpClient();
        SensorService sensorService = new SensorServiceClient(executorService, tcpClient);
        UI console = new UI(sensorService);
        console.run();
        executorService.shutdown();
    }
}
