/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class SocketController {
    public String serverName;
    public int serverPort;
    public List<Client> connectedClient;
    public List<Room> allRooms;
    ServerSocket s;
    public void OpenSocket(int port){
        try {
            s = new ServerSocket(port);
            connectedClient = new ArrayList<Client>();
            allRooms = new ArrayList<Room>();
            new Thread(() -> {
                try {
                    do{
                        System.out.println("Waiting for client");
                        Socket clientSocket= s.accept();
                        ClientCommunicateThread clientCommunicator = new ClientCommunicateThread(clientSocket);
			clientCommunicator.start();
                    }
                    while (s != null && !s.isClosed());
                } catch (Exception e) {
                    System.out.println("Server or client socket closed");
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void CloseSocket() {
        try {
            for(Client client: connectedClient)
                client.socket.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getThisIP(){
        String ip ="";
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("google.com", 80));
            ip = socket.getLocalAddress().getHostAddress();
            socket.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return ip;
    }
}
