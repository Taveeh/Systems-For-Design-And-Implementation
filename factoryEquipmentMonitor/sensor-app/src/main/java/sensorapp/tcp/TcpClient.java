package sensorapp.tcp;

import common.Message;
import common.SensorService;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class TcpClient {
    public Message sendAndReceive(Message request) {
        try (var socket = new Socket(SensorService.HOST, SensorService.PORT);
             var is = socket.getInputStream();
             var os = socket.getOutputStream()) {

            System.out.println("sending request: " + request);
            request.writeTo(os);
            System.out.println("request sent");

            Message response = new Message();
            response.readFrom(is);
            System.out.println("received response: " + response);

            return response;

        } catch (ConnectException ce) {
            throw new RuntimeException("Could not establish connection with the server");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("exception in send and receive", e);
        }

    }
}
