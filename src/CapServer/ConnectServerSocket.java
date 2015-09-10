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
	
	public ConnectServerSocket() {		//서버 소켓 생성
		try {
			serverSocket = new ServerSocket(portNum);
		} catch (IOException e) {
			System.err.println("다음의 포트번호에 연결할 수 없습니다.");
		}
	}
	
	public void acceptConnect(){		//클라이언트 소켓 연결
		try{
			clientSocket = serverSocket.accept();
			in = clientSocket.getInputStream();
		    inObj = new ObjectInputStream(in);
		    out = clientSocket.getOutputStream();
		    chk_out = new DataOutputStream(out);
		    outObj = new ObjectOutputStream(out);
		}
		catch(ClassCastException e){
			System.err.println("변환 오류");
		}
		catch(IOException e){
			System.err.println("accept() 실패");
		}
		catch(NullPointerException e){
			System.err.println("Accept Connect 실패");
			flag = false;
		}
	}
	
	public void sendData(String[] data){	//String 배열 형태로 데이터 보냄
		try{
		    outObj.writeObject(data);
		}
		catch(IOException e){
			System.err.println("쓰기 오류");
		}
	}
	
	public void sendData(double[] data){	//double 배열 형태로 데이터 보냄
		try{
		    outObj.writeObject(data);
		}
		catch(IOException e){
			System.err.println("쓰기 오류");
		}
	}
	
	public void sendData(int[] data){	//int 배열 형태로 데이터 보냄
		try{
			//out = clientSocket.getOutputStream();
		    //outObj = new ObjectOutputStream(out);
		    outObj.writeObject(data);
		}
		catch(IOException e){
			System.err.println("쓰기 오류");
		}
	}
	
	
	public String[] receiveData(){	//String 배열 형태로 데이터 받음
		
		try{
			//in = clientSocket.getInputStream();
		    //inObj = new ObjectInputStream(in);
			receive = (String[])inObj.readObject();
		    
		}
		catch(ClassNotFoundException e){
			System.err.println(e);
		}
		catch(IOException e){
			System.out.println("클라이언트 종료");
			this.setClientSocket(null);
		}
		return receive;
	}
	
	public int[] receiveIntData(){	//int 배열 형태로 데이터 받음
		try{
			//in = clientSocket.getInputStream();
		    //inObj = new ObjectInputStream(in);
		    receiveInt = (int[])inObj.readObject();
		}
		catch(ClassNotFoundException e){
			System.err.println("오류");
		}
		catch(IOException e){
			System.err.println("입출력 오류");
		}
		
		return receiveInt;
	}
	
	public double[] receivedoubleData(){	//double 배열 형태로 데이터 받음
		try{
			//in = clientSocket.getInputStream();
		    //inObj = new ObjectInputStream(in);
		    receiveDouble = (double[])inObj.readObject();
		}
		catch(ClassNotFoundException e){
			System.err.println("오류");
		}
		catch(IOException e){
			System.err.println("입출력 오류");
		}
		
		return receiveDouble;
	}
	
	public void verifyData(boolean confirm){
		try{
			//chk_out = new DataOutputStream(clientSocket.getOutputStream());
			chk_out.writeBoolean(confirm);
		}
		catch(ClassCastException e){
			System.err.println("변환 오류");
		}
		catch(IOException e){
			System.err.println("쓰기 오류");
		}
	}
	
	public void closeInputStream(){
		try{
			in.close();
			inObj.close();
		}
		catch(NullPointerException e){
			System.err.println("클라이언트 에러");
		}
		catch(IOException e){
			System.err.println("닫기 실패");
		}
	}
	
	public void closeDataOutputStream(){
		try{
			chk_out.close();
		}
		catch(NullPointerException e){
			System.err.println("클라이언트 에러");
		}
		catch(IOException e){
			System.err.println("닫기 실패");
		}
	}
	
	public void closeOutputstream(){
		try{
			out.close();
			outObj.close();
		}
		catch(NullPointerException e){
			System.err.println("클라이언트 에러");
		}
		catch(IOException e){
			System.err.println("닫기 실패");
		}
	}
	
	public void closeSocket(){
		try{
			clientSocket.close();
		}
		catch(NullPointerException e){
			System.err.println("에러");
		}
		catch(IOException e){
			System.err.println("닫기 실패");
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
