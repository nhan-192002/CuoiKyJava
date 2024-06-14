/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

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
        }

    }

    void CloseSocket() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
