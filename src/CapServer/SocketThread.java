package CapServer;

public class SocketThread extends Thread {

	private ConnectServerSocket servSock;
	private static String[] confirmdata = new String[20];
	
	final int SIZE = 100;
	
	public SocketThread(ConnectServerSocket sock) {
		this.servSock = sock;
	}

	public void run() {
		while (servSock.__isconnented()) {
			DBAccess_Table_info tempDBAccess_Table_info = new DBAccess_Table_info();
			DBAccess_Menu_info tempDBAccess_Menu_info = new DBAccess_Menu_info();
			DBAccess_Shop tempDBAccess_Shop = new DBAccess_Shop();
			DBAccess_Waiting tempDBAccess_Waiting = new DBAccess_Waiting();
			DBAccess_Table tempDBAccess_Table =  new DBAccess_Table();
			
			confirmdata = servSock.receiveData(); // 정보 받아서 id 맞는지 처리해야함
			if(servSock.getClientSocket()==null){	//소켓이 null이면 loop break
				break;
			}
			if (confirmdata[0].equals("login")) { // 로그인 할 때
				servSock.verifyData(tempDBAccess_Shop.checkDB(confirmdata[1], confirmdata[2]));
				
			}
			else if (confirmdata[0].equals("signup")) { // 가입할 때
				servSock.verifyData(tempDBAccess_Shop.checkidDB(confirmdata[1]));
			}
			else if (confirmdata[0].equals("editCustomerInfo")) { // 회원정보수정할 때
				tempDBAccess_Shop.alterDB_to_Shop(confirmdata[3], confirmdata[4], confirmdata[5], confirmdata[1], confirmdata[2], Integer.parseInt(confirmdata[6]));
								
			}
			else if (confirmdata[0].equals("requestMenuInfo")) { // 메뉴정보 뿌려주기
				
				tempDBAccess_Menu_info.getDB_from_Menu_info(confirmdata[1]);
				
				servSock.sendData(tempDBAccess_Menu_info.getShop_menuname_arr());
				servSock.sendData(tempDBAccess_Menu_info.getShop_menuprice_arr());
				servSock.sendData(tempDBAccess_Menu_info.getShop_menuinfo_arr());
			}
			else if (confirmdata[0].equals("requestTableInfo")) { // 테이블정보
				servSock.sendData(tempDBAccess_Table_info.getDB_from_Table_info(confirmdata[1]));
			}
			
			else if(confirmdata[0].equals("addMenuInfo")){
				//메뉴를 추가하기
				tempDBAccess_Menu_info.addDB_to_Menu_info(confirmdata[1], confirmdata[2], Integer.parseInt(confirmdata[3]), confirmdata[4]);
				
			}
			
			else if(confirmdata[0].equals("deleteMenuInfo")){
				//메뉴를 삭제하기
				tempDBAccess_Menu_info.deleteDB_to_Menu_info(confirmdata[1], confirmdata[2]);
				
			}
			
			else if(confirmdata[0].equals("editMenuInfo")){
				//메뉴의 정보를 바꾸기
				tempDBAccess_Menu_info.alterDB_to_Menu_info(confirmdata[1], confirmdata[2], Integer.parseInt(confirmdata[3]), confirmdata[4], confirmdata[5]);	
			}
			else if(confirmdata[0].equals("checkMenuName")){
				//기존에 메뉴 이름이 존재하는지 확인
				servSock.verifyData(tempDBAccess_Menu_info.checkMenuNameDB(confirmdata[1], confirmdata[2]));
			}
			
			else if(confirmdata[0].equals("requestWaitingInfo")){
				//각 음식점별 대기표를 리턴해줌
				tempDBAccess_Waiting.getDB_from_Waiting(confirmdata[1]);
				servSock.sendData(tempDBAccess_Waiting.getWaiting_Arr());
				servSock.sendData(tempDBAccess_Waiting.getWaiting_NumberArr());
				
				
			}
			else if(confirmdata[0].equals("deleteWaitingInfo")){
				//각 음식점별 대기표를 제거
				tempDBAccess_Waiting.deleteDB_to_Waiting(confirmdata[1], confirmdata[2]);
			}
			
			else if(confirmdata[0].equals("updateTableInfo")){
				
				tempDBAccess_Table_info.alterDB_to_Table_info(confirmdata[1], Integer.parseInt(confirmdata[2]));
			}
			else if(confirmdata[0].equals("updateTableInfoNew")){
				
				
				int[] tempGetTableNo = new int[SIZE];
				int[] tempGetTableState = new int[SIZE];
				double[] tempGetX = new double[SIZE];
				double[] tempGetY = new double[SIZE];
				int[] tempGetTableSize = new int[SIZE];
				
				tempGetTableNo = servSock.receiveIntData();
				tempGetTableState = servSock.receiveIntData();
				tempGetX = servSock.receivedoubleData();
				
				tempGetY = servSock.receivedoubleData();
				tempGetTableSize = servSock.receiveIntData();
				
				tempDBAccess_Table.updateDB_to_Table_Location(confirmdata[1], tempGetTableNo, tempGetTableState
						, tempGetX, tempGetY, tempGetTableSize);
				
			}
			else if(confirmdata[0].equals("getTableInfoNew")){
				
				
				tempDBAccess_Table.getDB_from_Table_Location(confirmdata[1]);	//shop_id
				servSock.sendData(tempDBAccess_Table.getTable_no());			//int arr
				servSock.sendData(tempDBAccess_Table.getTable_state()); 		//int arr
				servSock.sendData(tempDBAccess_Table.getX_coordinate());		//double arr
				servSock.sendData(tempDBAccess_Table.getY_coordinate());		//double arr
				servSock.sendData(tempDBAccess_Table.getTable_size());			//int arr
			}
			
			else if(confirmdata[0].equals("updateTableState")){
				
				tempDBAccess_Table.updateTableState(confirmdata[1], Integer.parseInt(confirmdata[2]), Integer.parseInt(confirmdata[3]));
			}
			
			
			
			

			
			else if(confirmdata[0].equals("requestMenuInfoApp")){
				//안드로이드 메뉴 정보 뿌려주기
				tempDBAccess_Menu_info.getDB_from_Menu_info(confirmdata[1]);
				
				servSock.sendData(tempDBAccess_Menu_info.getShop_menuname_arr());
				servSock.sendData(tempDBAccess_Menu_info.getShop_menuprice_arr());
				servSock.sendData(tempDBAccess_Menu_info.getShop_menuinfo_arr());
			}
			
			else if(confirmdata[0].equals("requestTableInfoApp")){
				int[] numberOfTable = new int[1];
				int[] temp;
				
				tempDBAccess_Shop.getOneDB_from_Shop(confirmdata[1]);
				numberOfTable[0] = tempDBAccess_Shop.getShop_numberOfTableAlone();
				
				temp = tempDBAccess_Table_info.getDB_from_Table_info(confirmdata[1]);

				servSock.sendData(numberOfTable);
				servSock.sendData(temp);
			}
			
			else if(confirmdata[0].equals("requestTableInfoAppNew")){
				
				
				tempDBAccess_Table.getDB_from_Table_Location(confirmdata[1]);
				
				servSock.sendData(tempDBAccess_Table.getTable_no());
				servSock.sendData(tempDBAccess_Table.getTable_state());
				servSock.sendData(tempDBAccess_Table.getX_coordinate());
				servSock.sendData(tempDBAccess_Table.getY_coordinate());
				servSock.sendData(tempDBAccess_Table.getTable_size());
				
				
				
			}
			
			else if(confirmdata[0].equals("sendingWaitingNameApp")){	
				//각 음식점별 대기표를 추가해줌
				tempDBAccess_Waiting.addDB_to_Waiting(confirmdata[1], confirmdata[2]);
				tempDBAccess_Waiting.getDB_from_Waiting(confirmdata[1]);
				servSock.sendData(tempDBAccess_Waiting.getWaiting_Arr());
				servSock.sendData(tempDBAccess_Waiting.getWaiting_NumberArr());
			}
			else if(confirmdata[0].equals("requestWaitingNumberApp")){
				//해당 사람의 대기 번호와 앞에 몇명 남았는지를 알려줌
				tempDBAccess_Waiting.getDB_from_Waiting(confirmdata[1]);
				servSock.sendData(tempDBAccess_Waiting.getWaiting_Arr());
				servSock.sendData(tempDBAccess_Waiting.getWaiting_NumberArr());
			}
			
			
			else if(confirmdata[0].equals("requestLocationInfoApp")){
				//모든 음식점에 대한 정보를 얻어올 때
				
				tempDBAccess_Shop.calculateDouble();
				
				servSock.sendData(tempDBAccess_Shop.getLatitude_arr());
				servSock.sendData(tempDBAccess_Shop.getLongdidude_arr());
				servSock.sendData(tempDBAccess_Shop.getShop_name());
				servSock.sendData(tempDBAccess_Shop.getShop_address());
				servSock.sendData(tempDBAccess_Shop.getShop_id());
			}
			/*
			else if(confirmdata[0].equals("localSearchListViewApp")){
				//지역별검색하고 싶을 때 서울시 모든 구 정보를 가져오기
				
				tempDBAccess_Seoul.get_from_Seoul();
				servSock.sendData(tempDBAccess_Seoul.getGu());
				servSock.sendData(tempDBAccess_Seoul.getDong());	
			}
			*/
			
			else if(confirmdata[0].equals("localSearchRestaurantApp")){
				//지역별검색하고 싶을 때 해당 지역의 음식점들을 반환해줌
				
				tempDBAccess_Shop.searchLocal(confirmdata[1]);
				
				servSock.sendData(tempDBAccess_Shop.getShop_id());
				servSock.sendData(tempDBAccess_Shop.getShop_name());
				servSock.sendData(tempDBAccess_Shop.getShop_address());	
				servSock.sendData(tempDBAccess_Shop.getShop_telnumber());	
				servSock.sendData(tempDBAccess_Shop.getShop_numberOfTable());	
				
			}
			
		}
		if(servSock.getClientSocket()!=null){
			servSock.closeInputStream();
			servSock.closeOutputstream();
			servSock.closeDataOutputStream();
			servSock.closeSocket();
		}
	}
}
