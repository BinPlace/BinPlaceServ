package CapServer;

public class ServerMain {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ServerControlForm c = new ServerControlForm();
		SocketManager sock = new SocketManager();
		sock.CreateServerSocket();
		sock.processConnect();
	}
}
