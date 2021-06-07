package server.service;

import common.SensorService;
import common.model.SensorDTO;
import server.repository.ServerAppRepository;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SensorServiceServer implements SensorService {
    private final ExecutorService executorService;
    private final ServerAppRepository serverAppRepository;

    public SensorServiceServer(ExecutorService executorService, ServerAppRepository serverAppRepository) {
        this.executorService = executorService;
        this.serverAppRepository = serverAppRepository;
    }

    @Override
    public CompletableFuture<String> sendSensor(SensorDTO sensorDTO) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(new Random().ints(0, 2000).findFirst().getAsInt());

            }catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(sensorDTO.toString());
            serverAppRepository.save(sensorDTO);
            return "OK";
        });
    }
}
