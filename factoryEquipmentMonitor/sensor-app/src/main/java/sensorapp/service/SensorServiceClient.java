package sensorapp.service;

import common.Convertor;
import common.Message;
import common.SensorService;
import common.model.SensorDTO;
import sensorapp.model.Sensor;
import sensorapp.tcp.TcpClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class SensorServiceClient implements SensorService {
    private final ExecutorService executorService;
    private final TcpClient tcpClient;

    public SensorServiceClient(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public CompletableFuture<String> sendSensor(SensorDTO sensorDTO) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(SensorService.SEND_SENSOR, Convertor.convertSensor(sensorDTO));
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        }, executorService);
    }
}
