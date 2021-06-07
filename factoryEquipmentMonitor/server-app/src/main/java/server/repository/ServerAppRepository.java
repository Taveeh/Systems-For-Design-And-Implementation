package server.repository;

import common.model.SensorDTO;

import java.sql.DriverManager;
import java.util.Date;

public class ServerAppRepository {
    private final String url;
    private final String user;
    private final String password;

    public ServerAppRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void save(SensorDTO sensorDTO) {
        String sqlCommand = "INSERT INTO sensor(name, measurement, time) VALUES (?, ?, ?)";
        try (var connection = DriverManager.getConnection(url, user, password);
             var preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setString(1, sensorDTO.getName());
            preparedStatement.setInt(2, sensorDTO.getMeasurement());
            preparedStatement.setLong(3, new Date().getTime());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
