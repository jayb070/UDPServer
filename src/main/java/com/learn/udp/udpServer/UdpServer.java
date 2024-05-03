package com.learn.udp.udpServer;

import javax.xml.crypto.Data;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class UdpServer {

	public static void main(String[] args) {
		try(DatagramSocket serverSocket = new DatagramSocket(4550)) {

			byte[] incomingData = new byte[65530];
			DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);


			while(true) {
				serverSocket.receive(incomingPacket);
				InetAddress clientAddress = incomingPacket.getAddress();
				int clientPort = incomingPacket.getPort();

				String clientMessage = new String(incomingPacket.getData(), 0, incomingPacket.getLength());
				System.out.println("Received from " + clientAddress + ":" + clientPort + " - " + clientMessage);


				String sendMsg = "Hi, happy to be connected!!";
				byte[] sendData = sendMsg.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
				serverSocket.send(sendPacket);

			}

		} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
