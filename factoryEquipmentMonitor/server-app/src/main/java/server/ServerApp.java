package server;

import common.Convertor;
import common.Message;
import common.SensorService;
import server.repository.ServerAppRepository;
import server.service.SensorServiceServer;
import server.tcp.TcpServer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class ServerApp {
    private static HashMap<String, String> readSettingsFile() {
        HashMap<String, String> propertiesMap = new HashMap<>();
        Properties properties = new Properties();

        String configFile = "./resources/programData/settings.properties";
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(configFile);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            return propertiesMap;
        }
        Stream.ofNullable(fileInputStream).findAny().ifPresentOrElse((el) -> {
            try {
                properties.load(fileInputStream);
                propertiesMap.put("database", properties.getProperty("database"));
                propertiesMap.put("user", properties.getProperty("user"));
                propertiesMap.put("password", properties.getProperty("password"));
            } catch (IOException ioException) {
                System.out.println("IOException: " + ioException.getMessage());
            }
        }, () -> System.out.println("Invalid config file"));

        return propertiesMap;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        TcpServer tcpServer = new TcpServer(executorService, SensorService.PORT);
        HashMap<String, String> properties = readSettingsFile();
        SensorService sensorService = new SensorServiceServer(executorService, new ServerAppRepository(
                "jdbc:postgresql://localhost:5432/factoryApp",
                "postgres",
                "branza123"
        ));

        tcpServer.addHandler(SensorService.SEND_SENSOR, request -> {
            try {
                String status = sensorService.sendSensor(Convertor.extractSensor(request.getBody())).get();
                return new Message(Message.OK, status);
            } catch (Exception e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });
        tcpServer.startServer();
    }
}
