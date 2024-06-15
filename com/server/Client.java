/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class Client {
    public String userName;
    public int port;
    public Socket socket;
    public BufferedReader receiver;
    public BufferedWriter sender;

    public Client(String userName, int port, Socket socket, BufferedReader receiver, BufferedWriter sender) {
        this.userName = userName;
        this.port = port;
        this.socket = socket;
        this.receiver = receiver;
        this.sender = sender;
    }

    public Client() {
    }
    public static Client findClient(List<Client> clientList, String userName) {
	for (Client client : clientList) {
            if (client.userName.equals(userName))
		return client;
	}
	return null;
    }
}
