package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;
import java.util.List;

public class Client {
	public String userName;
	public int port;
	public Socket socket;
//BufferedReader trong java được sử dụng để đọc văn bản từ một input stream dựa trên các ký tự (character stream).	
	public BufferedReader receiver;
//BufferedWriter trong java được sử dụng để cung cấp bộ đệm cho các các thể hiện của lớp Writer.
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public BufferedReader getReceiver() {
		return receiver;
	}

	public void setReceiver(BufferedReader receiver) {
		this.receiver = receiver;
	}

	public BufferedWriter getSender() {
		return sender;
	}

	public void setSender(BufferedWriter sender) {
		this.sender = sender;
	}
//tìm kiếm một client trong danh sách clientList dựa trên userName.
	public static Client findClient(List<Client> clientList, String userName) {
		for (Client client : clientList) {
			if (client.userName.equals(userName))
				return client;
		}
		return null;
	}

	@Override
	public String toString() {
		return "Client [userName=" + userName + ", port=" + port + ", socket=" + socket + ", receiver=" + receiver
				+ ", sender=" + sender + "]";
	}
	
}