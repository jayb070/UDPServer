package com.learn.udp.udpClient;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UdpClient {

    private final int SERVER_PORT;

    public UdpClient() {
        SERVER_PORT = 4550;
    }

    private InetAddress getServerAddress() throws UnknownHostException {
        return InetAddress.getLocalHost();
    }

    public void startCommunication() {

        try(DatagramSocket clientSocket = new DatagramSocket()) {

            String message = "Hi, udp server!";
            sendMessage(message, clientSocket);
            receiveMessage(clientSocket);

        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void sendMessage(String message, DatagramSocket clientSocket) throws IOException {
            InetAddress serverAddress = getServerAddress();
            byte[] sendMessage = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendMessage, sendMessage.length, serverAddress, SERVER_PORT);
            clientSocket.send(sendPacket);

    }

    private void receiveMessage(DatagramSocket clientSocket) throws IOException {
        byte[] receiveMessageBuf = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveMessageBuf, receiveMessageBuf.length);
        clientSocket.receive(receivePacket);
        String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Received from Server: " + receivedMessage);
    }
}
