package CapServer;

public class SocketManager {
	
	private ConnectServerSocket servSock;
	
	public void CreateServerSocket() {
		servSock = new ConnectServerSocket();
	}
	
	public void processConnect(){
		while(true){
			servSock.acceptConnect();
			if(servSock.flag==false){
				servSock.flag=true;
				continue;
			}
			SocketThread sthread = new SocketThread(servSock);
			sthread.start();
		}
	}
}