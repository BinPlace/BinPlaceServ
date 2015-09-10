package CapServer;

import java.io.*;
import java.net.*;

public class ConnectServerSocket {
	static ServerSocket serverSocket = null;
	private Socket clientSocket = null;
	final private int portNum = 6388;
	private InputStream in = null;
	private ObjectInputStream inObj = null;
	private OutputStream out = null;
	private ObjectOutputStream outObj = null;
	private DataOutputStream chk_out = null;
	
	boolean flag = true;
	
	private String[] receive = new String[1000];
	private int[] receiveInt = new int[1000];
	private double[] receiveDouble = new double[1000];
	
	public ConnectServerSocket() {		//���� ���� ����
		try {
			serverSocket = new ServerSocket(portNum);
		} catch (IOException e) {
			System.err.println("������ ��Ʈ��ȣ�� ������ �� �����ϴ�.");
		}
	}
	
	public void acceptConnect(){		//Ŭ���̾�Ʈ ���� ����
		try{
			clientSocket = serverSocket.accept();
			in = clientSocket.getInputStream();
		    inObj = new ObjectInputStream(in);
		    out = clientSocket.getOutputStream();
		    chk_out = new DataOutputStream(out);
		    outObj = new ObjectOutputStream(out);
		}
		catch(ClassCastException e){
			System.err.println("��ȯ ����");
		}
		catch(IOException e){
			System.err.println("accept() ����");
		}
		catch(NullPointerException e){
			System.err.println("Accept Connect ����");
			flag = false;
		}
	}
	
	public void sendData(String[] data){	//String �迭 ���·� ������ ����
		try{
		    outObj.writeObject(data);
		}
		catch(IOException e){
			System.err.println("���� ����");
		}
	}
	
	public void sendData(double[] data){	//double �迭 ���·� ������ ����
		try{
		    outObj.writeObject(data);
		}
		catch(IOException e){
			System.err.println("���� ����");
		}
	}
	
	public void sendData(int[] data){	//int �迭 ���·� ������ ����
		try{
			//out = clientSocket.getOutputStream();
		    //outObj = new ObjectOutputStream(out);
		    outObj.writeObject(data);
		}
		catch(IOException e){
			System.err.println("���� ����");
		}
	}
	
	
	public String[] receiveData(){	//String �迭 ���·� ������ ����
		
		try{
			//in = clientSocket.getInputStream();
		    //inObj = new ObjectInputStream(in);
			receive = (String[])inObj.readObject();
		    
		}
		catch(ClassNotFoundException e){
			System.err.println(e);
		}
		catch(IOException e){
			System.out.println("Ŭ���̾�Ʈ ����");
			this.setClientSocket(null);
		}
		return receive;
	}
	
	public int[] receiveIntData(){	//int �迭 ���·� ������ ����
		try{
			//in = clientSocket.getInputStream();
		    //inObj = new ObjectInputStream(in);
		    receiveInt = (int[])inObj.readObject();
		}
		catch(ClassNotFoundException e){
			System.err.println("����");
		}
		catch(IOException e){
			System.err.println("����� ����");
		}
		
		return receiveInt;
	}
	
	public double[] receivedoubleData(){	//double �迭 ���·� ������ ����
		try{
			//in = clientSocket.getInputStream();
		    //inObj = new ObjectInputStream(in);
		    receiveDouble = (double[])inObj.readObject();
		}
		catch(ClassNotFoundException e){
			System.err.println("����");
		}
		catch(IOException e){
			System.err.println("����� ����");
		}
		
		return receiveDouble;
	}
	
	public void verifyData(boolean confirm){
		try{
			//chk_out = new DataOutputStream(clientSocket.getOutputStream());
			chk_out.writeBoolean(confirm);
		}
		catch(ClassCastException e){
			System.err.println("��ȯ ����");
		}
		catch(IOException e){
			System.err.println("���� ����");
		}
	}
	
	public void closeInputStream(){
		try{
			in.close();
			inObj.close();
		}
		catch(NullPointerException e){
			System.err.println("Ŭ���̾�Ʈ ����");
		}
		catch(IOException e){
			System.err.println("�ݱ� ����");
		}
	}
	
	public void closeDataOutputStream(){
		try{
			chk_out.close();
		}
		catch(NullPointerException e){
			System.err.println("Ŭ���̾�Ʈ ����");
		}
		catch(IOException e){
			System.err.println("�ݱ� ����");
		}
	}
	
	public void closeOutputstream(){
		try{
			out.close();
			outObj.close();
		}
		catch(NullPointerException e){
			System.err.println("Ŭ���̾�Ʈ ����");
		}
		catch(IOException e){
			System.err.println("�ݱ� ����");
		}
	}
	
	public void closeSocket(){
		try{
			clientSocket.close();
		}
		catch(NullPointerException e){
			System.err.println("����");
		}
		catch(IOException e){
			System.err.println("�ݱ� ����");
		}
	}
	
	public boolean __isconnented(){
		
		boolean cState=false;
		
		cState = clientSocket.isConnected();
		return cState;
	}

	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
}
