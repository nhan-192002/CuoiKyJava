/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import java.net.ServerSocket;
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
        } catch (Exception e) {
        }

    }
}
